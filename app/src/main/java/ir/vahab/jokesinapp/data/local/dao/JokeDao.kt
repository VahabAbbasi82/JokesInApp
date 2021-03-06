package ir.vahab.jokesinapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ir.vahab.jokesinapp.domain.model.Joke
import kotlinx.coroutines.flow.Flow

@Dao
interface JokeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(jokes: List<Joke>)

    @Query("DELETE FROM tblJoke")
    suspend fun deleteAll()

    @Query("SELECT tblJoke.* " +
            "FROM tblJoke " +
            "WHERE tblJoke.category LIKE '%' || :searchQuery ||'%' " +
            "OR tblJoke.setup LIKE '%' || :searchQuery ||'%' " +
            "OR tblJoke.delivery LIKE '%' || :searchQuery ||'%'")
    fun getAll(searchQuery: String): Flow<List<Joke>>
}