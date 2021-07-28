package com.turk.footballrepository.dataSource

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import kotlinx.coroutines.flow.Flow

interface FootballMatchDataSource {

     fun getFootballMatches():Flow<Either<ErrorEntity,List<FootBallMatch>>>
}