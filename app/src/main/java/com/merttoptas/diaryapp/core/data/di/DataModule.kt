package com.merttoptas.diaryapp.core.data.di

import com.merttoptas.diaryapp.core.data.util.ConnectivityManagerNetworkMonitor
import com.merttoptas.diaryapp.core.data.util.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by mertcantoptas on 04.02.2023
 */

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}