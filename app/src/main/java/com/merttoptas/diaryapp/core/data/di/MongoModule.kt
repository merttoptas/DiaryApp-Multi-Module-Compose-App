package com.merttoptas.diaryapp.core.data.di

import com.merttoptas.diaryapp.core.data.repository.MongoRepository
import com.merttoptas.diaryapp.core.data.repository.impl.MongoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by mertcantoptas on 05.03.2023
 */

@Module
@InstallIn(ViewModelComponent::class)
interface MongoModule {

    @Binds
    fun bindMongoRepository(
        mongoRepositoryImpl: MongoRepositoryImpl
    ): MongoRepository
}