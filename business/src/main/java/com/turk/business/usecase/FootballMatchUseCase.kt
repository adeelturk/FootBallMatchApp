package com.turk.business.usecase

import android.util.Log
import com.turk.common.base.BaseUseCase
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.footballrepository.footballmatch.FootballMatchRepositoryImpl
import kotlinx.coroutines.flow.Flow

class FootballMatchUseCase(private val footballMatchRepo:FootballMatchRepositoryImpl):
    BaseUseCase<List<FootBallMatch>,FootballMatchUseCaseParams> (){

    override suspend fun run(param: FootballMatchUseCaseParams): Flow<Either<ErrorEntity,List<FootBallMatch>>> {

        return footballMatchRepo.getFootballMatches(param.date,param.isOnline,param.shouldClearCache)
    }
}

data class FootballMatchUseCaseParams(val date:String,val isOnline:Boolean,val shouldClearCache:Boolean)