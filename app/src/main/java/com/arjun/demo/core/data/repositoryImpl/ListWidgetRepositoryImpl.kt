package com.arjun.demo.core.data.repositoryImpl

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.data.repository.ListWidgetRepository
import com.arjun.demo.core.model.ListWidgetConfig
import com.arjun.demo.core.network.fake.ListFakeDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class ListWidgetRepositoryImpl(
    private val dataSource: ListFakeDataSource
) : ListWidgetRepository {

    override fun getListData(
        instanceId: String
    ): Flow<DataState<List<ListWidgetConfig>>> = flow {
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
