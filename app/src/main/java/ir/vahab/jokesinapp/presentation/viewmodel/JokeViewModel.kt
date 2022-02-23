package ir.vahab.jokesinapp.presentation.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahab.jokesinapp.data.local.preferences.PreferencesManager
import ir.vahab.jokesinapp.domain.usecase.GetJokesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokesUseCase: GetJokesUseCase,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    var jokesFlow = searchQuery.flatMapLatest { searchQuery ->
        getJokesUseCase(searchQuery, true)
    }
}