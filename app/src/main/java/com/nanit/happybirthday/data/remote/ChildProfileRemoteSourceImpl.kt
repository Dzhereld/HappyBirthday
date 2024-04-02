package com.nanit.happybirthday.data.remote

import com.nanit.happybirthday.domain.entity.ChildProfile
import com.nanit.happybirthday.domain.entity.ConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class ChildProfileRemoteSourceImpl @Inject constructor(): ChildProfileRemoteSource {
    override suspend fun connectToSocket(ipAddress: String, port: String): Flow<ConnectionResult> {
        return emptyFlow()
    }

    override suspend fun sendMessage(message: String) {
        // TODO: Needs to be implemented
    }

    override fun receiveChildProfile(): Flow<ChildProfile> {
        // TODO: Needs to be implemented
        return emptyFlow()
    }

    override suspend fun disconnectSocket() {
        // TODO: Needs to be implemented
    }
}