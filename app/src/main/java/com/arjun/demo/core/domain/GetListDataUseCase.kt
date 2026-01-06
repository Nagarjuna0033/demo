package com.arjun.demo.core.domain

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.data.repository.ListWidgetRepository
import com.arjun.demo.core.model.ListWidgetConfig
import kotlinx.coroutines.flow.Flow

class GetListDataUseCase(
    private val repository: ListWidgetRepository
) {
    operator fun invoke(instanceId: String): Flow<DataState<List<ListWidgetConfig>>> {
        return repository.getListData(instanceId)
    }
}