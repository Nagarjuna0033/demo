package com.arjun.demo.core.network.fake

import com.arjun.demo.core.model.ListWidgetConfig
import kotlinx.coroutines.delay

object ListFakeDataSource {
    suspend fun getData(instanceId: String): List<ListWidgetConfig> {
        delay(1200)
        if (instanceId == "shows") throw Exception("Error")
        return List(10) {
            ListWidgetConfig(instanceId, "Item $it")
        }
    }
}