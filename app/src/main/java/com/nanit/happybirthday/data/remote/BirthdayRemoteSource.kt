package com.nanit.happybirthday.data.remote

import com.nanit.happybirthday.domain.entity.BirthdayEvent
import kotlinx.coroutines.flow.Flow

interface BirthdayRemoteSource {
    suspend fun connectToSocket(
        ipAddress: String,
        port: Int
    ): Result<String>

    fun observeBirthdayEvent(): Flow<Result<BirthdayEvent>>

    suspend fun disconnectSocket()
}