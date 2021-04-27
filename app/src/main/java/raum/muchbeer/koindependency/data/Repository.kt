package raum.muchbeer.koindependency.data

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getFoods() = apiHelper.getFoods()
}