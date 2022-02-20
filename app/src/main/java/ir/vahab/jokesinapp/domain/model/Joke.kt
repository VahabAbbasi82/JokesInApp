package ir.vahab.jokesinapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblJoke")
data class Joke(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    @PrimaryKey
    val id: Int,
    val safe: Boolean,
    val lang: String
)