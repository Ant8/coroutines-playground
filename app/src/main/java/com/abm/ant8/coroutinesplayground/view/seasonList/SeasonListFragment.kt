package com.abm.ant8.coroutinesplayground.view.seasonList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.abm.ant8.coroutinesplayground.R
import kotlinx.android.synthetic.main.season_list_fragment.*

class SeasonListFragment : Fragment() {
    private val adapter by lazy { SeasonsListRecyclerAdapter() }

    companion object {
        fun newInstance() = SeasonListFragment()
    }

    private lateinit var viewModel: SeasonListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.season_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seasonsList.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeasonListViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            updateUI(viewModel.getSeasons())
        }
    }

    private fun updateUI(seasons: List<Int>) {
        with(adapter) {
            seasonsList.clear()
            seasonsList.addAll(seasons)
            notifyDataSetChanged()
        }
    }
}