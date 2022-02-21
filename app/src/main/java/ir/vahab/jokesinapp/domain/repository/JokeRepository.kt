package ir.vahab.jokesinapp.domain.repository

import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface JokeRepository {
    fun getAll(searchQuery: String, shouldFetch: Boolean): Flow<Resource<List<Joke>>>
}