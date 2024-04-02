package com.nanit.happybirthday.domain.repository

import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ConnectionResult
import kotlinx.coroutines.flow.Flow

interface ChildProfileRepository {
    suspend fun connectToServer(
        ipAddress: String,
        port: String
    ): Flow<ConnectionResult>

    suspend fun sendMessage(message: String)

    fun observeChildProfile(): Flow<ChildProfile>

    suspend fun disconnectFromServer()
}