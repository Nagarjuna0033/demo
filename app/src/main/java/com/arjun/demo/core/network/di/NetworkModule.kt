package com.arjun.demo.core.network.di

import com.arjun.demo.core.network.fake.BannerFakeDataSource
import com.arjun.demo.core.network.fake.ListFakeDataSource
import org.koin.dsl.module

val NetworkModule = module {
    single { BannerFakeDataSource }
    single { ListFakeDataSource }
}