package com.nanit.happybirthday.data.remote

import com.nanit.happybirthday.data.remote.entity.ChildProfileRemote
import com.nanit.happybirthday.data.remote.entity.mapToChildProfile
import com.nanit.happybirthday.domain.entity.ChildProfile
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.http.HttpMethod
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ChildProfileRemoteSourceImpl @Inject constructor(private val client: HttpClient) :
    ChildProfileRemoteSource {
    private var webSocket: WebSocketSession? = null

    override suspend fun connectToSocket(ipAddress: String, port: Int): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                println("connect To Socket: Success")
                webSocket = client.webSocketSession(
                    method = HttpMethod.Get,
                    host = ipAddress,
                    port = port,
                    path = PATH_CHILD_PROFILE
                )
                if (webSocket?.isActive == true) {
                    println("connect To Socket: Success")
                    Result.success("Success")
                } else {
                    println("connect To Socket: Failed")
                    Result.failure(IllegalStateException())
                }
            } catch (e: Exception) {
                println("connect To Socket: Failed")
                Result.failure(e)
            }
        }
    }

    override suspend fun sendMessage(message: String) {
        withContext(Dispatchers.IO) {
            try {
                webSocket?.send(Frame.Text(message))
                println("send Message: $message")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun receiveChildProfile(): Flow<ChildProfile> {
        return try {
            webSocket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText().orEmpty()
                    println("receive Message: $json")
                    val messageResponseDto = Json.decodeFromString<ChildProfileRemote>(json)
                    messageResponseDto.mapToChildProfile()
                } ?: flow { }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyFlow()
        }
    }

    override suspend fun disconnectSocket() {
        withContext(Dispatchers.IO) {
            try {
                webSocket?.close()
                println("disconnect Socket: Closed")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private companion object {
        const val PATH_CHILD_PROFILE = "/nanit"
    }
}