package com.arjun.demo.core.domain

import com.arjun.demo.core.common.DataState
import com.arjun.demo.core.data.repository.DashboardRepository
import com.arjun.demo.core.model.WidgetMeta
import kotlinx.coroutines.flow.Flow

class GetDashboardWidgetsUseCase(
    private val repository: DashboardRepository
) {
    operator fun invoke(): Flow<DataState<List<WidgetMeta>>> {
        return repository.getWidgets()
    }
}