package com.turk.runmockyio

import android.app.Application
import com.turk.business.footballMatchUseCase
import com.turk.footballmatch.footballMatchDependencies
import com.turk.footballrepository.footballMatchRepoBeans
import com.turk.network.networkModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        //region Koin Initializations
        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    networkModule,
                    footballMatchRepoBeans,
                    footballMatchUseCase,
                    footballMatchDependencies,
                )
            )
        }

        //endregion
    }

}