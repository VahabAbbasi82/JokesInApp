package ir.vahab.jokesinapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ir.vahab.jokesinapp.R
import ir.vahab.jokesinapp.data.local.preferences.PreferencesManager
import ir.vahab.jokesinapp.databinding.FragmentCounterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class CounterFragment : Fragment(R.layout.fragment_counter) {

    @Inject
    lateinit var preferencesManager : PreferencesManager
    lateinit var binding : FragmentCounterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterBinding.bind(view)
        init()
        binding.apply {
            val counter = runBlocking { preferencesManager.appCounter.first() }
            tvCounter.text = String.format("App Counter = $counter")
        }
        controller(view)
    }

    private fun controller(view: View) {
        val thread = object : Thread() {
            override fun run() {
                try {

                    sleep(3000L)
                    // navigate to jokes fragment
                    val action = CounterFragmentDirections.navigateToJokesFragment()
                    Navigation.findNavController(view).navigate(action)

                } catch (localException: Exception) {
                    Log.i("controller", "handler executed")
                }
            }
        }
        thread.start()
    }

    private fun init() {
        // next attempt for typing
        CoroutineScope(Dispatchers.IO).launch {
            preferencesManager.setAppCounter(preferencesManager.appCounter.first() + 1)
        }
    }

    override fun onResume() {
        super.onResume()
        view?.let { controller(it) }
    }
}