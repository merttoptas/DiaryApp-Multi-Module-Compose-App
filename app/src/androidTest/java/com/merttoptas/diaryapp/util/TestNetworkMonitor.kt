package com.merttoptas.diaryapp.util

import com.merttoptas.diaryapp.core.data.util.NetworkMonitor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Created by mertcantoptas on 04.02.2023
 */

class TestNetworkMonitor : NetworkMonitor {

    private val connectivityFlow = MutableStateFlow(true)

    override val isOnline: Flow<Boolean> = connectivityFlow

    /**
     * A test-only API to set the connectivity state from tests.
     */
    fun setConnected(isConnected: Boolean) {
        connectivityFlow.value = isConnected
    }
}
