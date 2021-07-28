package com.turk.footballmatch.state

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewAction
import com.turk.common.mvi.ViewState
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballmatch.action.FootballMatchAction

sealed class FootballMatchState:ViewState{

     object DEFAULT : FootballMatchState()
     object Loading : FootballMatchState()
     data class FootballMatchList(val data:List<FootBallMatch>):FootballMatchState()
     data class Error(val error: ErrorEntity) : FootballMatchState()
}
