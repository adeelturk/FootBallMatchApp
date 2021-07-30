package com.turk.footballrepository.footballmatch

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.dtos.serverObjects.FootballMatchResponse
import kotlinx.coroutines.flow.Flow

interface FootballMatchRepository {

     fun getFootballMatches(date:String,isOnline:Boolean,shouldClearCache:Boolean) :Flow<Either<ErrorEntity,List<FootBallMatch>>>
}