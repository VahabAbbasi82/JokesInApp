package ir.vahab.jokesinapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahab.jokesinapp.domain.usecase.GetJokesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(getJokesUseCase: GetJokesUseCase) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    val locksFlow = searchQuery.flatMapLatest { searchQuery ->
        getJokesUseCase(searchQuery)
    }
}