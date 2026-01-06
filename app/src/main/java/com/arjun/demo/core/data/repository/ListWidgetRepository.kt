package com.arjun.demo.core.data.repository

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.model.ListWidgetConfig
import kotlinx.coroutines.flow.Flow

interface ListWidgetRepository {
    fun getListData(instanceId: String): Flow<DataState<List<ListWidgetConfig>>>
}