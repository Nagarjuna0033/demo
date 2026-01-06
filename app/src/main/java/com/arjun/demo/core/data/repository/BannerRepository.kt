package com.arjun.demo.core.data.repository

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.model.BannerConfig
import kotlinx.coroutines.flow.Flow

interface BannerRepository {
    fun getBannerData(instanceId: String): Flow<DataState<List<BannerConfig>>>
}