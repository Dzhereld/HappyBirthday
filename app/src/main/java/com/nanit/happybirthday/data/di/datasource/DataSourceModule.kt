package com.nanit.happybirthday.data.di.datasource

import com.nanit.happybirthday.data.remote.BirthdayRemoteSource
import com.nanit.happybirthday.data.remote.BirthdayRemoteSourceImpl
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
    fun provideBirthdayRemoteSource(client: HttpClient): BirthdayRemoteSource{
        return BirthdayRemoteSourceImpl(client)
    }
}