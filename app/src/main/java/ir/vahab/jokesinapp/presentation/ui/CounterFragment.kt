package ir.vahab.jokesinapp.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ir.vahab.jokesinapp.R
import ir.vahab.jokesinapp.data.local.preferences.PreferencesManager
import ir.vahab.jokesinapp.databinding.FragmentCounterBinding
import ir.vahab.jokesinapp.presentation.viewmodel.CounterViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class CounterFragment : Fragment(R.layout.fragment_counter) {

    @Inject
    lateinit var preferencesManager : PreferencesManager
    private val viewModel : CounterViewModel by viewModels()
    lateinit var binding : FragmentCounterBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterBinding.bind(view)

        lifecycleScope.launchWhenCreated {
            viewModel.counter.collect {
                binding.tvCounter.text = String.format("App Counter = $it")
            }
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

    override fun onResume() {
        super.onResume()
        view?.let { controller(it) }
    }
}