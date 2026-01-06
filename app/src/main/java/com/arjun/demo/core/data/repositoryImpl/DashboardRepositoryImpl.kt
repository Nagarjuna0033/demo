package com.arjun.demo.core.data.repositoryImpl

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.data.repository.DashboardRepository
import com.arjun.demo.core.model.WidgetMeta
import com.arjun.demo.core.network.fake.DashboardFakeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val dataSource: DashboardFakeDataSource
) : DashboardRepository {
    override fun getWidgets(): Flow<DataState<List<WidgetMeta>>> = flow {
        emit(DataState.Loading)

        delay(5000)

        val data = dataSource.getWidgets()
        if (data.isEmpty()) emit(DataState.Empty)
        else emit(DataState.Success(data))
    }
    .catch { e ->
        emit(DataState.Error(e.message ?: "Error"))
    }
    .flowOn(Dispatchers.IO)
}