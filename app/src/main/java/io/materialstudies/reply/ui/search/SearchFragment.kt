package io.materialstudies.reply.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialSharedAxis
import io.materialstudies.reply.R
import io.materialstudies.reply.data.SearchSuggestion
import io.materialstudies.reply.data.SearchSuggestionStore
import io.materialstudies.reply.databinding.FragmentSearchBinding
import io.materialstudies.reply.databinding.SearchSuggestionItemBinding
import io.materialstudies.reply.databinding.SearchSuggestionTitleBinding

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        setUpSuggestions(binding.searchSuggestionContainer)
    }

    private fun setUpSuggestions(suggestionContainer: ViewGroup) {
        addSuggestionTitleView(suggestionContainer, R.string.search_suggestion_title_yesterday)
        addSuggestionItemViews(suggestionContainer, SearchSuggestionStore.YESTERDAY_SUGGESTIONS)
        addSuggestionTitleView(suggestionContainer, R.string.search_suggestion_title_this_week)
        addSuggestionItemViews(suggestionContainer, SearchSuggestionStore.THIS_WEEK_SUGGESTIONS)
    }

    private fun addSuggestionTitleView(
        parent: ViewGroup,
        @StringRes titleResId: Int
    ) {
       val inflater = LayoutInflater.from(parent.context)
       val titleBinding = SearchSuggestionTitleBinding.inflate(inflater, parent, false)
        titleBinding.title = titleResId
        parent.addView(titleBinding.root)
    }

    private fun addSuggestionItemViews(
        parent: ViewGroup,
        suggestions: List<SearchSuggestion>
    ) {
        suggestions.forEach {
            val inflater = LayoutInflater.from(parent.context)
            val suggestionBinding = SearchSuggestionItemBinding.inflate(inflater, parent, false)
            suggestionBinding.suggestion = it
            parent.addView(suggestionBinding.root)
        }
    }
}