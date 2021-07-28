package com.turk.footballrepository.dataSource

import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.dtos.mapper.FootBallMatchNetworkDataMapperImpl
import com.turk.footballrepository.FootballMatchApiService
import com.turk.network.requestBlocking
import com.turk.network.requestBlockingFlow
import kotlinx.coroutines.flow.Flow

class FootballMatchDataSourceImpl(private val footballMatchApiService:FootballMatchApiService,
                                  private val mapper:FootBallMatchNetworkDataMapperImpl):
    FootballMatchDataSource{

    override  fun getFootballMatches():Flow<Either<ErrorEntity,List<FootBallMatch>>>{

      return  footballMatchApiService.getFootballMatchesData("bc1ce3b7-6ad2-4fef-af6c-08f8865b210e").requestBlockingFlow{

          mapper.mapFromEntity(it)
      }
    }
}