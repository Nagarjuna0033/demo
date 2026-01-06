package com.arjun.demo.core.network.fake

import com.arjun.demo.core.model.WidgetMeta

object DashboardFakeDataSource {
    fun getWidgets(): List<WidgetMeta> {
        return listOf(
            WidgetMeta.Banner("pokemon"),
            WidgetMeta.Banner("cars"),
            WidgetMeta.Banner("bikes"),
            WidgetMeta.List("movies"),
            WidgetMeta.List("shows")
        )
    }
}
