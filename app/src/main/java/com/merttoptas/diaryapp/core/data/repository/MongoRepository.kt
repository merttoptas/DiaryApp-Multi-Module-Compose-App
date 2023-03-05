package com.merttoptas.diaryapp.core.data.repository

import com.merttoptas.diaryapp.core.common.DataState
import com.merttoptas.diaryapp.core.data.model.Diary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Created by mertcantoptas on 02.03.2023
 */

typealias Diaries = DataState<Map<LocalDate, List<Diary>>>

interface MongoRepository {
    fun configureTheRealm()
    suspend fun getAllDiaries(): Flow<Diaries>
}