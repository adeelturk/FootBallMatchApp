package com.turk.footballmatch

import com.turk.footballmatch.viewmodel.FootballMatchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val footballMatchDependencies = module {
    viewModel { FootballMatchViewModel(get(),get()) }
}