package com.arjun.demo.core.network.fake

import com.arjun.demo.core.model.BannerConfig

object BannerFakeDataSource {
    fun getData(instanceId: String): List<BannerConfig> {
        return when (instanceId) {
            "pokemon" -> listOf(
                BannerConfig("1", "Pikachu", "Electric"),
                BannerConfig("2", "Charizard", "Fire"),
                BannerConfig("3", "Bulbasaur", "Grass"),
                BannerConfig("4", "Squirtle", "Water")
            )

            "cars" -> listOf(
                BannerConfig("5", "Tesla Model S", "Electric"),
                BannerConfig("6", "BMW M5", "Sedan"),
                BannerConfig("7", "Audi R8", "Sports"),
                BannerConfig("8", "Toyota Supra", "Performance")
            )

            "bikes" -> listOf(
                BannerConfig("9", "Kawasaki Ninja", "Sport Bike"),
                BannerConfig("10", "Royal Enfield Classic", "Cruiser"),
                BannerConfig("11", "Ducati Panigale", "Superbike"),
                BannerConfig("12", "Harley Davidson", "Touring")
            )

            else -> listOf(
                BannerConfig("13", "Default Banner", "Generic")
            )
        }
    }
}