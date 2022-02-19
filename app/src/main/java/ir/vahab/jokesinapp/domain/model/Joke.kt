package ir.vahab.jokesinapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblJoke")
data class Joke(
    val category : String,
    @PrimaryKey
    val id : Int,
    val joke : String,
    val safe : Boolean,
)