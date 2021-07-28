package com.turk.footballmatch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.turk.business.usecase.FootballMatchUseCase
import com.turk.common.base.BaseUseCase
import com.turk.common.base.BaseViewModel
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.reducer.FootballMatchesListReducer
import com.turk.footballmatch.state.FootballMatchState
import kotlinx.coroutines.flow.*

class FootballMatchViewModel(private val getFootballMatchUseCase: FootballMatchUseCase) :
    BaseViewModel<FootballMatchState, FootballMatchAction>(
        FootballMatchState.DEFAULT,
        FootballMatchesListReducer()
    ) {

    override fun processAction(action:FootballMatchAction) {
        super.processAction(action)
        when(action){

            is FootballMatchAction.FetchFootballMatchResults->{

                fetchFootballMatchList()
            }
        }
    }

    private val _footballMatchList = MutableLiveData<List<FootBallMatch>>()
    val footballMatchList: LiveData<List<FootBallMatch>>
        get() = _footballMatchList

   private fun fetchFootballMatchList() {

        getFootballMatchUseCase(viewModelScope = viewModelScope, BaseUseCase.None()) {
            it.either(::handleFailure) {dataList->

                dispatch(FootballMatchAction.DeliverFootballMatchResults(dataList))
                _footballMatchList.postValue(dataList)
            }
        }


    }
}

