package com.nanit.happybirthday.data.repository

import com.nanit.happybirthday.data.remote.ChildProfileRemoteSource
import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.repository.ChildProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChildProfileRepositoryImpl @Inject constructor(private val remoteDataSource: ChildProfileRemoteSource) :
    ChildProfileRepository {
    override suspend fun connectToServer(ipAddress: String, port: Int): Result<String> {
        return remoteDataSource.connectToSocket(ipAddress, port)
    }

    override suspend fun sendMessage(message: String): Result<String> {
        return remoteDataSource.sendMessage(message)
    }

    override fun observeChildProfile(): Flow<ChildProfile> {
        return remoteDataSource.receiveChildProfile()
    }

    override suspend fun disconnectFromServer() {
        remoteDataSource.disconnectSocket()
    }

}