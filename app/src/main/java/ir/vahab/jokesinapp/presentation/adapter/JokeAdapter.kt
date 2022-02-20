package ir.vahab.jokesinapp.presentation.adapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.vahab.jokesinapp.databinding.ItemJokeBinding
import ir.vahab.jokesinapp.domain.model.Joke

class JokeAdapter : ListAdapter<Joke, JokeAdapter.ViewHolder>(DiffCallback) {

    var searchQuery = ""
        set(value) {
            field = value
            DiffCallback.searchQuery = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeAdapter.ViewHolder {
        val binding = ItemJokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JokeAdapter.ViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    private fun highLightText(spannable: Spannable, string: String, start: Int) {
        val startIndex =  string.lowercase()
            .indexOf(searchQuery.lowercase())
        if (startIndex > -1) {
            val endIndex = startIndex + searchQuery.length
            spannable.setSpan(ForegroundColorSpan(Color.YELLOW), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    inner class ViewHolder(private val binding : ItemJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(joke: Joke) {
            binding.apply {
                val jokeCategory = joke.category
                val jokeText = joke.setup

                if (searchQuery.isNotEmpty()) {
                    val lockNameSpannable = SpannableString(jokeCategory)
                    highLightText(lockNameSpannable, jokeCategory, 0)
                    itemJokeCategory.text = lockNameSpannable

                    val lockDetailSpannable = SpannableString(jokeText)
                    highLightText(lockDetailSpannable, jokeText, 0)
                    itemJokeText.text = lockDetailSpannable
                } else {
                    itemJokeCategory.text = jokeCategory
                    itemJokeText.text = jokeText
                }
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Joke>() {
        var searchQuery = ""
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke) =
            oldItem == newItem &&
                    !oldItem.category.lowercase().contains(searchQuery.lowercase()) &&
                    !oldItem.setup.lowercase().contains(searchQuery.lowercase()) &&
                    !oldItem.delivery.lowercase().contains(searchQuery.lowercase())
    }
}