package com.arjun.demo.core.model

sealed class WidgetMeta(open val instanceId: String) {

    data class Banner(
        override val instanceId: String
    ) : WidgetMeta(instanceId)

    data class List(
        override val instanceId: String
    ) : WidgetMeta(instanceId)
}