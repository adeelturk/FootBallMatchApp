package com.turk.footballmatch.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.turk.common.base.BaseFragment
import com.turk.common.base.BaseViewModel
import com.turk.common.base.GeneralAdapter
import com.turk.common.error.ErrorEntity
import com.turk.common.extension.fault
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballmatch.BR
import com.turk.footballmatch.R
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.databinding.FootballListFragmentBinding
import com.turk.footballmatch.state.FootballMatchState
import com.turk.footballmatch.viewmodel.FootballMatchViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FootballMatchListFragment : BaseFragment<FootballListFragmentBinding,FootballMatchState,FootballMatchAction>() {

    private val footballMatchViewModel: FootballMatchViewModel by sharedViewModel()

    override fun layoutResourceId(): Int =R.layout.football_list_fragment

    override fun getViewModel(): BaseViewModel<FootballMatchState, FootballMatchAction> =footballMatchViewModel

    private val adapter = GeneralAdapter(BR.match, R.layout.football_match_item, FootBallMatch.DIFF_CALLBACK)

    override fun initialize(savedInstanceState: Bundle?) {

        binding.viewModel = footballMatchViewModel
        binding.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {

            fetchData()
        }

        fetchData()
    }


    private fun fetchData(){
        dispatchIntent(FootballMatchAction.FetchFootballMatchResults)
    }


    override fun attachListeners() {
        super.attachListeners()
        fault(footballMatchViewModel.errorEntity,::handleFailure)

    }

    override fun handleFailure(errorEntity: ErrorEntity?) {
        super.handleFailure(errorEntity)
        errorEntity?.run {
            dispatchIntent(FootballMatchAction.Error(this))
        }

    }

    override fun handleState(state: FootballMatchState) {

        when(state){
            is FootballMatchState.Loading->{
                binding.swipeRefresh.isRefreshing=true

            }
            is FootballMatchState.FootballMatchList->{

                binding.swipeRefresh.isRefreshing=false
            }
            is FootballMatchState.Error->{

                binding.swipeRefresh.isRefreshing=false
            }
            else->{

            }

        }

    }
}