package com.abm.ant8.coroutinesplayground.view.seasonDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.abm.ant8.coroutinesplayground.R

class SeasonDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = SeasonDetailsFragment()
    }

    private lateinit var viewModel: SeasonDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.season_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SeasonDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
