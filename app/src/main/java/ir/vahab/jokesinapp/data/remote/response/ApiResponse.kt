package ir.vahab.jokesinapp.data.remote.response

import ir.vahab.jokesinapp.domain.model.Joke

data class ApiResponse(
    val amount : Int,
    val error : Boolean,
    val jokes : List<Joke>
)