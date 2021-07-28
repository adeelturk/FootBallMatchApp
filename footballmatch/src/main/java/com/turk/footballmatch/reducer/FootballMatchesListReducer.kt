package com.turk.footballmatch.reducer

import com.turk.common.mvi.Reducer
import com.turk.footballmatch.action.FootballMatchAction
import com.turk.footballmatch.state.FootballMatchState

class FootballMatchesListReducer: Reducer<FootballMatchState, FootballMatchAction> {

    override fun reduce(
        currentState: FootballMatchState,
        action: FootballMatchAction
    ): FootballMatchState {

       return  when (action){

           is FootballMatchAction.DEFAULT->{

               FootballMatchState.DEFAULT
           }
            is FootballMatchAction.FetchFootballMatchResults->{

                stateForFetchingResults()
            }

          is  FootballMatchAction.DeliverFootballMatchResults->{

              stateAfterFootballMatchesFetched(action)
           }
           is FootballMatchAction.Error->{
               stateOnError(action)
           }

           else -> {
               currentState
           }
        }
    }


    private fun stateForFetchingResults(): FootballMatchState {

        return FootballMatchState.Loading
    }

    private fun stateAfterFootballMatchesFetched( action: FootballMatchAction.DeliverFootballMatchResults): FootballMatchState {

        return FootballMatchState.FootballMatchList(action.data)
    }

    private fun stateOnError(action: FootballMatchAction.Error):FootballMatchState{
        return FootballMatchState.Error(action.error)
    }



}