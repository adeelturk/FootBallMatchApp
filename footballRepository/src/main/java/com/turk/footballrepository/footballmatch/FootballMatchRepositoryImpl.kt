package com.turk.footballrepository.footballmatch

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballrepository.dataSource.FootballMatchDataSourceImpl
import kotlinx.coroutines.flow.Flow

class FootballMatchRepositoryImpl(private val footballMatchDataSource: FootballMatchDataSourceImpl) :FootballMatchRepository {

    override  fun getFootballMatches(): Flow<Either<ErrorEntity, List<FootBallMatch>>> {

        return footballMatchDataSource.getFootballMatches()
    }
}