package com.arjun.demo.core.domain

import com.arjun.demo.core.data.repository.BannerRepository

class GetBannerDataUseCase(
    private val repository: BannerRepository
) {
    operator fun invoke(instanceId: String) =
        repository.getBannerData(instanceId)
}