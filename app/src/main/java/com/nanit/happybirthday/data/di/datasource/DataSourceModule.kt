package com.nanit.happybirthday.data.di.datasource

import com.nanit.happybirthday.data.remote.ChildProfileRemoteSource
import com.nanit.happybirthday.data.remote.ChildProfileRemoteSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun provideChildProfileRemoteSource(impl: ChildProfileRemoteSourceImpl): ChildProfileRemoteSource
}