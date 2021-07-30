package com.turk.footballmatch.ui

import android.os.Bundle
import com.turk.common.base.BaseFragment
import com.turk.common.base.BaseViewModel
import com.turk.footballmatch.R
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.databinding.FootballmatchDetailFragmentBinding
import com.turk.footballmatch.state.FootballMatchState
import com.turk.footballmatch.viewmodel.FootballMatchViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FootBallMatchDetailScreen : BaseFragment<FootballmatchDetailFragmentBinding, FootballMatchState, FootballMatchAction>() {


    override fun layoutResourceId(): Int =R.layout.footballmatch_detail_fragment


    private val footballMatchViewModel: FootballMatchViewModel by sharedViewModel()

    override fun getViewModel(): BaseViewModel<FootballMatchState, FootballMatchAction> =footballMatchViewModel

    override fun initialize(savedInstanceState: Bundle?) {


    }

    override fun handleState(state: FootballMatchState) {
        when(state) {
            is FootballMatchState.SelectedFootballMatch -> {


                binding.match=state.selectedFootballMatch
            }
        }
    }
}