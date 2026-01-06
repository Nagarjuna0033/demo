package com.arjun.demo.core.data.repository

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.model.WidgetMeta
import kotlinx.coroutines.flow.Flow

interface DashboardRepository {
    fun getWidgets(): Flow<DataState<List<WidgetMeta>>>
}