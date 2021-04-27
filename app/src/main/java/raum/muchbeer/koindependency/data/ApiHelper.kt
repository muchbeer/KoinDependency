package raum.muchbeer.koindependency.data

import raum.muchbeer.koindependency.model.Food
import raum.muchbeer.koindependency.model.FoodModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getFoods(): Response<FoodModel>
}