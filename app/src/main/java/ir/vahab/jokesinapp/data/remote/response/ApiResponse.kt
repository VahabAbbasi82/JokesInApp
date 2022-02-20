package ir.vahab.jokesinapp.data.remote.response

import ir.vahab.jokesinapp.domain.model.Joke

data class ApiResponse(
    val error : Boolean,
    val amount : Int,
    val jokes : List<Joke>
)