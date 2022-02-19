package ir.vahab.jokesinapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ir.vahab.jokesinapp.R
import ir.vahab.jokesinapp.databinding.FragmentJokesBinding

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes) {

    private lateinit var binding: FragmentJokesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokesBinding.bind(view)
    }
}