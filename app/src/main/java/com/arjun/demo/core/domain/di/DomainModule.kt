package com.arjun.demo.core.domain.di

import com.arjun.demo.core.domain.GetBannerDataUseCase
import com.arjun.demo.core.domain.GetListDataUseCase
import org.koin.dsl.module

val DomainModule = module {
    single { GetListDataUseCase(get()) }

    single { GetBannerDataUseCase(get()) }
}