package com.arjun.demo.core.data.repositoryImpl

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.data.repository.BannerRepository
import com.arjun.demo.core.model.BannerConfig
import com.arjun.demo.core.model.ListWidgetConfig
import com.arjun.demo.core.network.fake.BannerFakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class BannerRepositoryImpl(
    private val dataSource: BannerFakeDataSource
) : BannerRepository {

    override fun getBannerData(
        instanceId: String
    ): Flow<DataState<List<BannerConfig>>> = flow {
        val data = dataSource.getData(instanceId)

        if (data.isEmpty()) {
            emit(DataState.Empty)
        } else {
            emit(DataState.Success(data))
        }
    }.catch { e ->
        emit(
            DataState.Error(
                message = e.message ?: "Error",
                throwable = e
            )
        )
    }
}