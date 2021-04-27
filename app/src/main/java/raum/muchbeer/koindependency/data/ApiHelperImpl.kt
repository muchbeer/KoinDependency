package raum.muchbeer.koindependency.data

import raum.muchbeer.koindependency.BuildConfig
import raum.muchbeer.koindependency.model.Food
import raum.muchbeer.koindependency.model.FoodModel
import retrofit2.Response

class ApiHelperImpl(private val apiService: FoodService) : ApiHelper {

    override suspend fun getFoods(): Response<FoodModel> = apiService.getFoods(BuildConfig.API_KEY, true)

}