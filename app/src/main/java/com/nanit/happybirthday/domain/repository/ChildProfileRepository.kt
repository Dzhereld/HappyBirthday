package com.nanit.happybirthday.domain.repository

import com.nanit.happybirthday.domain.entity.ChildProfile
import kotlinx.coroutines.flow.Flow

interface ChildProfileRepository {
    suspend fun connectToServer(
        ipAddress: String,
        port: Int
    ): Result<String>

    fun observeBirthdayEvent(): Flow<Result<ChildProfile>>

    suspend fun disconnectFromServer()
}