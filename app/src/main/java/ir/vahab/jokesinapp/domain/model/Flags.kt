package ir.vahab.jokesinapp.domain.model

import androidx.room.Entity

//@Entity(tableName = "tblFlag")
data class Flags(
    val idJoke : Int,
    val explicit : Boolean,
    val nsfw : Boolean,
    val political : Boolean,
    val racist : Boolean,
    val religious : Boolean,
    val sexist : Boolean
)