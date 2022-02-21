package ir.vahab.jokesinapp.domain.usecase

import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.repository.JokeRepository
import ir.vahab.jokesinapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetJokesUseCaseImpl(
    private val jokeRepository: JokeRepository
) : GetJokesUseCase {
    override fun invoke(searchQuery: String, shouldFetch: Boolean): Flow<Resource<List<Joke>>> {
        return jokeRepository.getAll(searchQuery, shouldFetch)
    }
}