package raum.muchbeer.koindependency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import raum.muchbeer.koindependency.adapter.FoodAdapter
import raum.muchbeer.koindependency.data.Repository
import raum.muchbeer.koindependency.data.Status
import raum.muchbeer.koindependency.di.module.AppModule
import raum.muchbeer.koindependency.di.module.RepositoryModule
import raum.muchbeer.koindependency.di.module.ViewModelModule
import raum.muchbeer.koindependency.model.Food
import raum.muchbeer.koindependency.viewmodel.FoodViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : FoodViewModel by viewModel()
    private lateinit var adapter : FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI()
        setObserver()
    }

    private fun setUI() {
        recyleView.layoutManager = GridLayoutManager(this@MainActivity, 2)
        adapter = FoodAdapter(arrayListOf())
   /*     recyleView.addItemDecoration(
            DividerItemDecoration(
                recyleView.context,
                (recyleView.layoutManager as LinearLayoutManager).orientation
            )
        )*/
        recyleView.adapter = adapter
    }

    private fun setObserver() {
        viewModel.food.observe(this) {foodStatus->
            when (foodStatus.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    recyleView.visibility = View.VISIBLE
                    networkStatus.visibility = View.GONE
                    foodStatus.data.let {
                       renderList(it!!)
                    }
                }
                Status.ERROR -> {
                    recyleView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    networkStatus.visibility = View.VISIBLE
                    networkStatus.text =  foodStatus.message
                }

                Status.LOADING -> {
                    recyleView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    networkStatus.visibility = View.GONE
                }
            }
        }
    }


    private fun renderList(foods: List<Food>) {
        adapter.setFood(foods)
        adapter.notifyDataSetChanged()
    }
}