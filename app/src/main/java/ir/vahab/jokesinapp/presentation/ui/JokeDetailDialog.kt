package ir.vahab.jokesinapp.presentation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import ir.vahab.jokesinapp.databinding.DialogJokeDetailBinding
import ir.vahab.jokesinapp.databinding.FragmentJokesBinding
import ir.vahab.jokesinapp.domain.model.Joke

class JokeDetailDialog : DialogFragment() {

    private lateinit var binding: DialogJokeDetailBinding
    private val args: JokeDetailDialogArgs by navArgs()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return DialogJokeDetailBinding.inflate(inflater, container, false).root
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogJokeDetailBinding.bind(view)
        init()

        args.joke?.let { setup(it) }

        binding.btDismiss.setOnClickListener { dismiss() }
    }

    private fun setup(joke: Joke) {
        binding.apply {
            tvJokeCategory.text = joke.category
            tvJokeSetup.text = joke.setup
            tvJokeDelivery.text = joke.delivery
        }
    }

    private fun init() {
//        dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
//        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog!!.setCancelable(true)
//        dialog!!.setCanceledOnTouchOutside(true)
    }
}