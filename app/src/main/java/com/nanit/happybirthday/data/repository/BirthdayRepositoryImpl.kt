package com.nanit.happybirthday.data.repository

import com.nanit.happybirthday.data.remote.BirthdayRemoteSource
import com.nanit.happybirthday.domain.entity.BirthdayEvent
import com.nanit.happybirthday.domain.repository.BirthdayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BirthdayRepositoryImpl @Inject constructor(private val remoteDataSource: BirthdayRemoteSource) :
    BirthdayRepository {
    override suspend fun connectToServer(ipAddress: String, port: Int): Result<String> {
        return remoteDataSource.connectToSocket(ipAddress, port)
    }

    override fun observeBirthdayEvent(): Flow<Result<BirthdayEvent>> {
        return remoteDataSource.observeBirthdayEvent()
    }

    override suspend fun disconnectFromServer() {
        remoteDataSource.disconnectSocket()
    }

}