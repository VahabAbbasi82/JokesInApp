package ir.vahab.jokesinapp.data.repository

import androidx.room.withTransaction
import ir.vahab.jokesinapp.data.local.AppDB
import ir.vahab.jokesinapp.data.remote.AppApi
import ir.vahab.jokesinapp.data.remote.response.ApiResponse
import ir.vahab.jokesinapp.data.util.networkBoundResource
import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.repository.JokeRepository
import ir.vahab.jokesinapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class JokeRepositoryImpl(
    private val appApi: AppApi,
    private val appDB: AppDB
) : JokeRepository {

    override fun getAll(
        searchQuery: String,
        shouldFetch: Boolean
    ): Flow<Resource<List<Joke>>> = networkBoundResource(
        query = {
            appDB.jokeDao().getAll(searchQuery)
        },
        fetch = {
            appApi.getData("twopart", 10)
        },
        saveFetchResult = { result ->
            appDB.withTransaction {

                appDB.jokeDao().deleteAll()

                val jokes = result.jokes
                appDB.jokeDao().insertAll(jokes)
            }
        },
        shouldFetch = {
            shouldFetch
        }
    )
}