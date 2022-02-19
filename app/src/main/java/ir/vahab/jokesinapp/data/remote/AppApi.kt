package ir.vahab.jokesinapp.data.remote

import ir.vahab.jokesinapp.data.remote.response.ApiResponse
import retrofit2.http.GET

interface AppApi {

    companion object {
        const val BASE_URL = "https://v2.jokeapi.dev/joke/"
    }

    @GET("Any?type=single&amount=10")
    suspend fun getData(): ApiResponse
}