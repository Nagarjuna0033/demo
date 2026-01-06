package com.arjun.demo.navigation.di

import com.arjun.demo.core.data.di.DataModule
import com.arjun.demo.core.network.di.NetworkModule
import com.arjun.demo.navigation.RootModule
import org.koin.dsl.module


object KoinModules {

    val CoreModules = module{
        includes(
            NetworkModule,
            DataModule,
        )
    }

    val allModules = listOf(
        RootModule,
        CoreModules,
    )
}