package ir.vahab.jokesinapp.presentation.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.fragment.app.DialogFragment
import ir.vahab.jokesinapp.databinding.DialogJokeDetailBinding
import ir.vahab.jokesinapp.databinding.FragmentJokesBinding

class JokeDetailDialog : DialogFragment() {

    private lateinit var binding: DialogJokeDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DialogJokeDetailBinding.bind(view)
        init()
        binding.btDismiss.setOnClickListener { dismiss() }
    }

    private fun init() {
        dialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(true)
        dialog!!.setCanceledOnTouchOutside(true)
    }
}