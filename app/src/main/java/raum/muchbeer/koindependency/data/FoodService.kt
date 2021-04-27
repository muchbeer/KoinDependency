package raum.muchbeer.koindependency.data

import raum.muchbeer.koindependency.model.FoodModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FoodService {
    @Headers("Content-Type: application/json")
    @GET("recipes/complexSearch")
    suspend fun getFoods(@Query("apiKey") apiKey : String,
                         @Query("includeNutritition") incldeNutrn : Boolean): Response<FoodModel>

}