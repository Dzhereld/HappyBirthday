package com.nanit.happybirthday.data.di

import com.nanit.happybirthday.data.repository.BirthdayRepositoryImpl
import com.nanit.happybirthday.domain.repository.BirthdayRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun provideBirthdayRepository(impl: BirthdayRepositoryImpl): BirthdayRepository
}