package com.turk.business

import com.turk.business.usecase.FootballMatchUseCase
import org.koin.dsl.module


val footballMatchUseCase= module{

    single { FootballMatchUseCase(get()) }
}