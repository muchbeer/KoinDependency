package raum.muchbeer.koindependency.di.module

import org.koin.dsl.module
import raum.muchbeer.koindependency.data.Repository

class RepositoryModule {

    val repoModule = module {
        single { Repository(get()) }
    }
}