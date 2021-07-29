package com.turk.footballmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.turk.business.usecase.FootballMatchUseCase
import com.turk.business.usecase.FootballMatchUseCaseParams
import com.turk.common.base.BaseUseCase
import com.turk.common.base.BaseViewModel
import com.turk.common.livedata.ConnectionLiveData
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.reducer.FootballMatchesListReducer
import com.turk.footballmatch.state.FootballMatchState
import kotlinx.coroutines.flow.*
import org.koin.android.ext.android.inject

class FootballMatchViewModel(private val getFootballMatchUseCase: FootballMatchUseCase, val connectionLiveData: ConnectionLiveData) :
    BaseViewModel<FootballMatchState, FootballMatchAction>(
        FootballMatchState.DEFAULT,
        FootballMatchesListReducer()
    ) {


    override fun processAction(action:FootballMatchAction) {
        super.processAction(action)
        when(action){

            is FootballMatchAction.FetchFootballMatchResults->{

                fetchFootballMatchList(action.date,action.isOnline)
            }
            else -> {}
        }
    }

    private val _footballMatchList = MutableLiveData<List<FootBallMatch>>()
    val footballMatchList: LiveData<List<FootBallMatch>>
        get() = _footballMatchList

   private fun fetchFootballMatchList(date:String,isOnline:Boolean) {

        getFootballMatchUseCase(viewModelScope = viewModelScope, FootballMatchUseCaseParams(date,isOnline)) {
            it.either(::handleFailure) {dataList->

                dispatch(FootballMatchAction.DeliverFootballMatchResults(dataList))
                _footballMatchList.postValue(dataList)
            }
        }


    }
}

