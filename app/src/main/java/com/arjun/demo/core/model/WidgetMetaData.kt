package com.arjun.demo.core.model

sealed class WidgetMeta {
    data class Banner(val instanceId: String) : WidgetMeta()
    data class List(val instanceId: String) : WidgetMeta()
}