package raum.muchbeer.koindependency.di.module

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import raum.muchbeer.koindependency.viewmodel.FoodViewModel

class ViewModelModule {

    val viewmodelModule = module {
        viewModel {
            FoodViewModel(get(), get())
        }
    }
}