package com.nanit.happybirthday.data.remote

import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ConnectionResult
import kotlinx.coroutines.flow.Flow

interface ChildProfileRemoteSource {
    suspend fun connectToSocket(
        ipAddress: String,
        port: String
    ): Flow<ConnectionResult>

    suspend fun sendMessage(message: String)

    fun receiveChildProfile(): Flow<ChildProfile>

    suspend fun disconnectSocket()
}