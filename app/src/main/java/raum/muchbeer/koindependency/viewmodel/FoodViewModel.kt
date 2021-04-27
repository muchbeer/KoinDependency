package raum.muchbeer.koindependency.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import raum.muchbeer.koindependency.model.Food
import raum.muchbeer.koindependency.data.Repository
import raum.muchbeer.koindependency.data.Resource
import raum.muchbeer.koindependency.utility.NetworkHelper
import raum.muchbeer.koindependency.utility.NetworkStatusLiveData

class FoodViewModel(
    private val mainRepository: Repository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _foods = MutableLiveData<Resource<List<Food>>>()
    val food: LiveData<Resource<List<Food>>>
        get() = _foods

  //  private val _isInternet = MutableLiveData<Boolean>()
 //   val isInternet: LiveData<Boolean> = networkHelper

   // val isInternetMutable = MutableLiveData<Boolean>()

   // val mediator = MediatorLiveData<Unit>()

    init {
       /* mediator.addSource(isInternet, {
            isInternetMutable.value = it
        })*/
        fetchFood()
    }

    private fun fetchFood() {
        viewModelScope.launch {
            _foods.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getFoods().let {
                    if (it.isSuccessful) {
                        _foods.postValue(Resource.success(it.body()!!.results))
                    } else _foods.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _foods.postValue(Resource.error("No internet connection", null))
        }
    }
}
