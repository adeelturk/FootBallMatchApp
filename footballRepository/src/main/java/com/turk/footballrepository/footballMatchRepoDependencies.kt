package com.turk.footballrepository

import com.turk.common.livedata.ConnectionLiveData
import com.turk.dtos.mapper.FootBallMatchNetworkDataMapperImpl
import com.turk.footballrepository.dataSource.FootballMatchDataSourceImpl
import com.turk.footballrepository.dataSource.local.RoomSupport
import com.turk.footballrepository.footballmatch.FootballMatchRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val footballMatchRepoBeans= module {

    //retrofit
    single { get<Retrofit>().create(FootballMatchApiService::class.java) }

    // Dto Mapper
    single { FootBallMatchNetworkDataMapperImpl() }


    // Room
    single { RoomSupport(get()) }
    single { get<RoomSupport>().getFootBallMatchDao() }

    single {ConnectionLiveData(get()) }
    //dataSource
    single { FootballMatchDataSourceImpl(get(),get(),get())}



    //repositories
    single { FootballMatchRepositoryImpl(get()) }


}

