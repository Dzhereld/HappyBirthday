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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.IOException
import javax.inject.Inject

class ChildProfileRemoteSourceImpl @Inject constructor(
    private val client: HttpClient
) :
    ChildProfileRemoteSource {
    private var webSocket: WebSocketSession? = null

    private var webSocketScope: CoroutineScope? = null

    private val receiveMessageStateFlow = MutableStateFlow("")

    override suspend fun connectToSocket(ipAddress: String, port: Int): Result<String> {
        webSocketScope?.cancel()
        webSocketScope = CoroutineScope(Dispatchers.IO)
        return withContext(Dispatchers.IO) {
            try {
                println("connecting To Socket")
                webSocket = client.webSocketSession(
                    method = HttpMethod.Get,
                    host = ipAddress,
                    port = port,
                    path = PATH_CHILD_PROFILE
                )
                if (webSocket?.isActive == true) {
                    println("connect To Socket: Success")
                    try {
                        webSocket?.incoming
                            ?.receiveAsFlow()
                            ?.filter { it is Frame.Text }
                            ?.mapNotNull {
                                (it as? Frame.Text)
                                    ?.readText()
                                    .also {
                                        println("receive Message: $it")
                                    }
                            }
                            ?.reTranslateTo(receiveMessageStateFlow, webSocketScope)
                        Result.success("Success")
                    } catch (e: Exception) {
                        e.printStackTrace()
                        println("error after receiving message: ${e.message}")
                        Result.failure(e)
                    }
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

    override suspend fun sendMessage(message: String): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                webSocket?.send(Frame.Text(message))
                println("send Message: $message")
                Result.success("Success")
            } catch (e: Exception) {
                println("send Message: Failed")
                Result.failure(e)
            }
        }
    }

    override fun receiveChildProfile(): Flow<ChildProfile> {
        return receiveMessageStateFlow.asStateFlow()
            .mapNotNull { message ->
                if (message.isNotEmpty()) {
                    Json.decodeFromString<ChildProfileRemote>(message).mapToChildProfile()
                } else {
                    null
                }
            }
            .filter { it.ageInMonths <= NINE_YEARS_IN_MONTH }
    }

    override suspend fun disconnectSocket() {
        withContext(Dispatchers.IO) {
            try {
                webSocket?.close()
                webSocketScope?.cancel()
                println("disconnect Socket: Closed")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun <T> Flow<T>.reTranslateTo(
        flow : MutableStateFlow<T>,
        scope: CoroutineScope?
    ): StateFlow<T> {
        scope?.launch {
            this@reTranslateTo.collect(flow)
        }
        return flow
    }

    private companion object {
        const val PATH_CHILD_PROFILE = "/nanit"

        const val NINE_YEARS_IN_MONTH = 108
    }
}