package ir.vahab.jokesinapp.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.vahab.jokesinapp.R
import ir.vahab.jokesinapp.databinding.FragmentJokesBinding
import ir.vahab.jokesinapp.domain.model.Joke
import ir.vahab.jokesinapp.domain.util.Resource
import ir.vahab.jokesinapp.presentation.adapter.JokeAdapter
import ir.vahab.jokesinapp.presentation.viewmodel.JokeViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes), JokeAdapter.OnItemClickListener {

    private val viewModel : JokeViewModel by viewModels()
    private lateinit var binding : FragmentJokesBinding
    private lateinit var jokeAdapter : JokeAdapter
    private lateinit var searchView : SearchView

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentJokesBinding.bind(view)
        init()
        subscribe()

        binding.apply {
            listSwiper.setOnRefreshListener {

                lifecycleScope.launchWhenResumed {
                    viewModel.jokesFlow.collectLatest {
                        progressBar.isVisible = it is Resource.Loading

                        when (it) {
                            is Resource.Error -> it.error?.let { error ->
                                Toast.makeText(requireActivity(), error.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                            is Resource.Success -> it.data?.let { data ->
                                jokeAdapter.submitList(data)
                                jokeAdapter.searchQuery = viewModel.searchQuery.value
                            }
                            else -> {}
                        }
                    }
                }
                listSwiper.isRefreshing = false
            }
        }

//        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {} })
    }

    private fun init() {
        jokeAdapter = JokeAdapter(this)
        binding.apply {
            recyclerView.apply {
                adapter = jokeAdapter
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
            }
        }
    }

    private fun subscribe() {
        binding.apply {
            lifecycleScope.launchWhenResumed {
                viewModel.jokesFlow.collectLatest {
                    progressBar.isVisible = it is Resource.Loading

                    when (it) {
                        is Resource.Error -> it.error?.let { error ->
                            Toast.makeText(requireActivity(), error.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                        is Resource.Success -> it.data?.let { data ->
                            jokeAdapter.submitList(data)
                            jokeAdapter.searchQuery = viewModel.searchQuery.value
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onItemClicked(joke: Joke) {
        val action = JokesFragmentDirections.actionJokesFragmentToJokeDetailDialog(joke = joke)
        findNavController().navigate(action)
//        val action = JokesFragmentDirections.actionGlobalJokeDetailDialog()
//        findNavController().navigate(action)

//        this@JokesFragment.parentFragmentManager.let {
//            JokeDetailDialog().show(it, "JokeDetail")
//        }
    }
}