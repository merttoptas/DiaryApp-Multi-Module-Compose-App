package com.merttoptas.diaryapp.core.data.repository

import com.merttoptas.diaryapp.core.common.DataState
import com.merttoptas.diaryapp.core.data.model.Diary
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

/**
 * Created by mertcantoptas on 02.03.2023
 */
interface MongoRepository {
    fun configureTheRealm()
    fun getAllDiaries(): Flow<DataState<Map<LocalDate, List<Diary>>>>
}