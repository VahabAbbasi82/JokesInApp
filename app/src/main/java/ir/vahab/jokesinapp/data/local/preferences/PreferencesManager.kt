package ir.vahab.jokesinapp.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.EmptyCoroutineContext.plus

private const val PREFERENCES_NAME = "app_preferences"

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext appContext: Context) {

    companion object {
        val APP_COUNTER = intPreferencesKey("app_counter")
    }

    private val dataStore = appContext.dataStore

    suspend fun setAppCounter(appCounter: Int) {
        dataStore.edit { preferences ->
            preferences[APP_COUNTER] = appCounter
        }
    }

    val appCounter: Flow<Int> = dataStore.data.map { preferences ->
        preferences[APP_COUNTER] ?: 1
    }
}