package ir.vahab.jokesinapp.domain.usecase

import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetJokesUseCase {
    operator fun invoke(searchQuery: String): Flow<Resource<List<Joke>>>
}