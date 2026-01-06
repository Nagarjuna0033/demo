package com.arjun.demo.navigation.di

import com.arjun.demo.core.data.di.DataModule
import com.arjun.demo.core.domain.di.DomainModule
import com.arjun.demo.core.network.di.NetworkModule
import com.arjun.demo.feature.home.di.HomeModule
import com.arjun.demo.navigation.RootModule
import org.koin.dsl.module


object KoinModules {

    val CoreModules = module {
        includes(
            NetworkModule,
            DataModule,
            DomainModule
        )
    }

    val FeaturesModules = module {
        includes(
            HomeModule
        )
    }

    val allModules = listOf(
        RootModule,
        CoreModules,
        FeaturesModules
    )
}