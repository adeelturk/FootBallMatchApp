package com.turk.footballmatch.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.turk.common.base.BaseFragment
import com.turk.common.base.BaseViewModel
import com.turk.common.base.GeneralAdapter
import com.turk.common.error.ErrorEntity
import com.turk.common.extension.fault
import com.turk.common.extension.observe
import com.turk.common.livedata.ConnectionLiveData
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballmatch.BR
import com.turk.footballmatch.R
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.databinding.FootballListFragmentBinding
import com.turk.footballmatch.state.FootballMatchState
import com.turk.footballmatch.viewmodel.FootballMatchViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class FootballMatchListFragment : BaseFragment<FootballListFragmentBinding,FootballMatchState,FootballMatchAction>() {

    private val footballMatchViewModel: FootballMatchViewModel by sharedViewModel()

    override fun layoutResourceId(): Int =R.layout.football_list_fragment

    override fun getViewModel(): BaseViewModel<FootballMatchState, FootballMatchAction> =footballMatchViewModel

    private val adapter = GeneralAdapter(BR.match, R.layout.football_match_item, FootBallMatch.DIFF_CALLBACK)



    override fun initialize(savedInstanceState: Bundle?) {

        binding.viewModel = footballMatchViewModel
        binding.adapter = adapter
        adapter.clickListener={footballMatch,View->

            dispatchIntent(FootballMatchAction.OpenDetailsForSelectedFootballMatch(footballMatch))
        }


        binding.swipeRefresh.setOnRefreshListener {

            fetchData(footballMatchViewModel.connectionLiveData.value?:true)
        }

        fetchData(footballMatchViewModel.connectionLiveData.value?:true)
    }



    private fun fetchData(isOnline:Boolean){
        dispatchIntent(FootballMatchAction.FetchFootballMatchResults("23745f3a-5eaa-43cf-ab46-737eb273596b","bc1ce3b7-6ad2-4fef-af6c-08f8865b210e",isOnline))
    }


    override fun attachListeners() {
        super.attachListeners()
        fault(footballMatchViewModel.errorEntity,::handleFailure)

        observe(footballMatchViewModel.connectionLiveData){isConnected->

            if(!isConnected){

                binding.internetStatusBanner.visibility= View.VISIBLE
            }else{
                binding.internetStatusBanner.visibility= View.GONE
            }

        }

        launchOnLifecycleScope {

            footballMatchViewModel.footballMatchListState.collectLatest {

                adapter.submitList(it)
            }
        }
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

                if(state.error is ErrorEntity.NetworkConnection ){
                    binding.internetStatusBanner.visibility= View.VISIBLE
                    fetchData(false)
                }
                binding.swipeRefresh.isRefreshing=false
            }
            is FootballMatchState.SelectedFootballMatch->{
                findNavController().navigate(R.id.action_footballMatchListFragment_to_footBallMatchDetailScreen)

            }
            else->{

            }

        }

    }
}