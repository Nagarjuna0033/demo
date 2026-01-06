package com.arjun.demo.core.network.fake

import com.arjun.demo.core.model.BannerConfig

object BannerFakeDataSource {
    fun getData(instanceId: String): List<BannerConfig> =
        when (instanceId) {
            "pokemon" -> listOf(
                BannerConfig("Pikachu", "Electric"),
                BannerConfig("Charizard", "Fire")
            )
            else -> listOf(
                BannerConfig("Default", "Banner")
            )
        }
}