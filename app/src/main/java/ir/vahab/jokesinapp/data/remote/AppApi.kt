package ir.vahab.jokesinapp.data.remote

import ir.vahab.jokesinapp.data.remote.response.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {
//    https://v2.jokeapi.dev/joke/Any?type=twopart&amount=10
    companion object {
        const val BASE_URL = "https://v2.jokeapi.dev/joke/"
    }

    @GET("Any")
    suspend fun getData(
        @Query("type") type: String,
        @Query("amount") amount: Int
    ): ApiResponse



//    @Query("blacklistFlags") blacklistFlags: List<String>,
}