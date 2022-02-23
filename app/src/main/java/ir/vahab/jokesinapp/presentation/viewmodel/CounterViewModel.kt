package ir.vahab.jokesinapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vahab.jokesinapp.data.local.preferences.PreferencesManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CounterViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    private val _counter = MutableStateFlow(preferencesManager.appCounter)
    val counter = _counter.flatMapLatest {
        preferencesManager.appCounter
    }

    private fun runAppCounter() {
        runBlocking { preferencesManager.setAppCounter(
            _counter.value.first() + 1
        ) }
    }

    override fun onCleared() {
        runAppCounter()
        super.onCleared()
    }
}