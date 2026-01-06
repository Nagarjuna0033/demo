package com.arjun.demo.core.network.fake

import com.arjun.demo.core.model.ListWidgetConfig

object ListFakeDataSource {
    fun getData(instanceId: String): List<ListWidgetConfig> {

        val cinemaNames = listOf(
            "Kalki",
            "Business man",
            "Pokiri",
            "Bahubali",
            "Athadu",
            "Khaleja",
            "HIT: The Third Case",
            "Darling",
            "Run Raja Run",
            "Okkadu"
        )

        return cinemaNames.mapIndexed { index, name ->
            ListWidgetConfig(id = index.toString(), name = instanceId, surname = name)
        }
    }
}