package ir.vahab.jokesinapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.vahab.jokesinapp.data.local.dao.JokeDao
import ir.vahab.jokesinapp.domain.model.Joke

@Database(entities = [Joke::class], version = 1)
abstract class AppDB : RoomDatabase() {

    companion object {
        const val DB_NAME = "app_db"
    }

    abstract fun jokeDao(): JokeDao
}