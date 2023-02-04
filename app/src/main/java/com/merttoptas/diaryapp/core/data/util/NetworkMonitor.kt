package com.merttoptas.diaryapp.core.data.util

import kotlinx.coroutines.flow.Flow

/**
 * Created by mertcantoptas on 04.02.2023
 */

interface NetworkMonitor {
    val isOnline: Flow<Boolean>
}
