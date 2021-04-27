package raum.muchbeer.koindependency.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import raum.muchbeer.koindependency.BuildConfig
import raum.muchbeer.koindependency.data.ApiHelper
import raum.muchbeer.koindependency.data.ApiHelperImpl
import raum.muchbeer.koindependency.data.FoodService
import raum.muchbeer.koindependency.utility.NetworkHelper
import raum.muchbeer.koindependency.utility.NetworkStatusLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AppModule {

    val appModule = module {
        single {
            provideOkHttpClient()
        }
        single {
            provideRetrofit(get(), BuildConfig.BASE_URL
            )
        }
        single { provideApiService(get()) }

        single { provideNetworkHelper(androidContext()) }

        single { provideLiveNetwork(androidContext()) }

        //inject Interface objectss
        single<ApiHelper> {
            return@single ApiHelperImpl(get())
        }
    }

    private fun provideLiveNetwork(context: Context) = NetworkStatusLiveData(context)

    private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

    private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    private fun provideApiService(retrofit: Retrofit): FoodService = retrofit.create(FoodService::class.java)


}