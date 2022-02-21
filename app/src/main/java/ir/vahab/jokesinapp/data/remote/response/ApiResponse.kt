package ir.vahab.jokesinapp.data.remote.response

import ir.vahab.jokesinapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

data class ApiResponse(
    val error : Boolean,
    val amount : Int,
    val jokes : List<Joke>
)