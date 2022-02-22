package ir.vahab.jokesinapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tblJoke")
@Parcelize
data class Joke(
    val category: String,
    val type: String,
    val setup: String,
    val delivery: String,
    @PrimaryKey
    val id: Int,
    val safe: Boolean,
    val lang: String
) : Parcelable