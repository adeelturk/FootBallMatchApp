package com.turk.footballmatch.action

import com.turk.common.error.ErrorEntity
import com.turk.common.mvi.ViewAction
import com.turk.dtos.footballmatch.FootBallMatch

sealed class FootballMatchAction:ViewAction{
     object DEFAULT : FootballMatchAction()
     data class FetchFootballMatchResults(val date:String,val isOnline:Boolean) : FootballMatchAction()
     data class Error(val error:ErrorEntity) : FootballMatchAction()
     data class DeliverFootballMatchResults(val data:List<FootBallMatch>) : FootballMatchAction()


}
