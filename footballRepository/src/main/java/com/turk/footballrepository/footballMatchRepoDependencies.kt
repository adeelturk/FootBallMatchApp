package com.turk.footballrepository

import com.turk.dtos.mapper.FootBallMatchNetworkDataMapperImpl
import com.turk.footballrepository.dataSource.FootballMatchDataSourceImpl
import com.turk.footballrepository.footballmatch.FootballMatchRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val footballMatchRepoBeans= module {

    //retrofit
    single { get<Retrofit>().create(FootballMatchApiService::class.java) }

    // Dto Mapper
    single { FootBallMatchNetworkDataMapperImpl() }
    //dataSource
    single { FootballMatchDataSourceImpl(get(),get()) }

    //repositories
    single { FootballMatchRepositoryImpl(get()) }




}

