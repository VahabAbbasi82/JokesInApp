package ir.vahab.jokesinapp.presentation.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahab.jokesinapp.domain.usecase.GetJokesUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    private val getJokesUseCase: GetJokesUseCase,
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    var jokesFlow = searchQuery.flatMapLatest { searchQuery ->
        getJokesUseCase(searchQuery, true)
    }
}