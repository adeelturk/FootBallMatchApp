package com.turk.footballrepository.dataSource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import com.turk.common.error.ErrorEntity
import com.turk.common.functional.Either
import com.turk.common.livedata.ConnectionLiveData
import com.turk.common.util.OnlineStatusListener
import com.turk.dtos.footballmatch.FootBallMatch
import com.turk.dtos.mapper.FootBallMatchNetworkDataMapperImpl
import com.turk.footballrepository.FootballMatchApiService
import com.turk.footballrepository.dataSource.local.source.FootBallMatchDao
import com.turk.network.requestBlockingFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FootballMatchDataSourceImpl(private val footballMatchApiService:FootballMatchApiService,
                                  private val mapper:FootBallMatchNetworkDataMapperImpl,
                                  private val footballMatchDao:FootBallMatchDao

):
    FootballMatchDataSource{

    override  fun getFootballMatches(date:String,isOnline:Boolean):Flow<Either<ErrorEntity,List<FootBallMatch>>>{


        return if(!isOnline!! && footballMatchDao.getAllFootballMatchesList.count()>0){
            flow { emit(Either.Right(mapper.mapFromEntity(footballMatchDao.getAllFootballMatchesList))) }
        }else{

            footballMatchApiService.getFootballMatchesData(date).requestBlockingFlow{

                footballMatchDao.clearTable()
                footballMatchDao.addResult(it)
                mapper.mapFromEntity(it)

            }
        }

    }




}