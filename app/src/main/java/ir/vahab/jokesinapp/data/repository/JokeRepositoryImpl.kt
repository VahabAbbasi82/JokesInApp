package ir.vahab.jokesinapp.data.repository

import android.util.Log
import androidx.room.withTransaction
import ir.vahab.jokesinapp.data.local.AppDB
import ir.vahab.jokesinapp.data.remote.AppApi
import ir.vahab.jokesinapp.data.util.networkBoundResource
import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.repository.JokeRepository
import ir.vahab.jokesinapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JokeRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val appDB: AppDB
) : JokeRepository {

    override fun getAll(searchQuery: String): Flow<Resource<List<Joke>>> = networkBoundResource(
        query = {
            appDB.jokeDao().getAll(searchQuery)
        },
        fetch = {
            appApi.getJokeData("twopart", 10) //listOf("nsfw"),"twopart", 10)
        },
        saveFetchResult = { result ->
            appDB.withTransaction {
                appDB.jokeDao().deleteAll()
                appDB.jokeDao().insertAll(result.jokes)
            }
        },
        shouldFetch = {
            // Database Refreshing policy
            true
        }
    )

    val TAG = "JokeRepositoryImpl"
}