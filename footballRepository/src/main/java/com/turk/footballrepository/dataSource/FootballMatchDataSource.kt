package com.turk.footballrepository.dataSource

import androidx.lifecycle.LifecycleOwner
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import kotlinx.coroutines.flow.Flow

interface FootballMatchDataSource {

     fun getFootballMatches(date:String,isOnline:Boolean,shouldClearCache:Boolean):Flow<Either<ErrorEntity,List<FootBallMatch>>>
}