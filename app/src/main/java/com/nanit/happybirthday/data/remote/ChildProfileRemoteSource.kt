package com.nanit.happybirthday.data.remote

import com.nanit.happybirthday.domain.entity.ChildProfile
import kotlinx.coroutines.flow.Flow

interface ChildProfileRemoteSource {
    suspend fun connectToSocket(
        ipAddress: String,
        port: Int
    ): Result<String>

    suspend fun sendMessage(message: String)

    fun receiveChildProfile(): Flow<ChildProfile>

    suspend fun disconnectSocket()
}