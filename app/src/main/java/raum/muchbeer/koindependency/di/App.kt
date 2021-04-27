package raum.muchbeer.koindependency.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import raum.muchbeer.koindependency.di.module.AppModule
import raum.muchbeer.koindependency.di.module.RepositoryModule
import raum.muchbeer.koindependency.di.module.ViewModelModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(AppModule().appModule, RepositoryModule().repoModule, ViewModelModule().viewmodelModule))
        }
    }
}