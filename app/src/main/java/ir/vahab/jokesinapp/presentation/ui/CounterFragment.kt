package ir.vahab.jokesinapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.vahab.jokesinapp.R
import ir.vahab.jokesinapp.data.local.preferences.PreferencesManager
import javax.inject.Inject

@AndroidEntryPoint
class CounterFragment : Fragment(R.layout.fragment_counter) {

    @Inject
    lateinit var preferencesManager : PreferencesManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}