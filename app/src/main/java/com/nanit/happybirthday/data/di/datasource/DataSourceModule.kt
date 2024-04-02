package com.nanit.happybirthday.data.di.datasource

import com.nanit.happybirthday.data.remote.ChildProfileRemoteSource
import com.nanit.happybirthday.data.remote.ChildProfileRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Singleton
    @Provides
    fun provideChildProfileRemoteSource(client: HttpClient): ChildProfileRemoteSource{
        return ChildProfileRemoteSourceImpl(client)
    }
}