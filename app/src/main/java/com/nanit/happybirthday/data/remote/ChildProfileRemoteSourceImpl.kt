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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.joda.time.DateTime
import org.joda.time.Months
import javax.inject.Inject

class ChildProfileRemoteSourceImpl @Inject constructor(
    private val client: HttpClient
) :
    ChildProfileRemoteSource {

    private var webSocketSession: WebSocketSession? = null

    private var webSocketScope: CoroutineScope? = null

    private val receiveMessageStateFlow = MutableStateFlow("")

    override suspend fun connectToSocket(ipAddress: String, port: Int): Result<String> {
        webSocketScope?.cancel()
        webSocketScope = CoroutineScope(Dispatchers.IO)
        return withContext(Dispatchers.IO) {
            try {
                println("connecting To Socket")
                webSocketSession = client.webSocketSession(
                    method = HttpMethod.Get,
                    host = ipAddress,
                    port = port,
                    path = PATH_NANIT
                )
                val socketConnection = webSocketSession
                if (socketConnection?.isActive == true) {
                    println("connect To Socket: Success")
                    sendMessageToGetBirthdayInfo(socketConnection)
                    return@withContext startObservingIncomingMessage(socketConnection)
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

    private suspend fun sendMessageToGetBirthdayInfo(session: WebSocketSession): Result<Unit> {
        return runCatching {
            session.send(Frame.Text(COMMAND_TO_GET_BIRTHDAY))
        }.onSuccess {
            println("send message to get birthday info: Success")
        }.onFailure {
            println("send message to get birthday info: Failed")
        }
    }

    private fun startObservingIncomingMessage(socketConnection: WebSocketSession): Result<String> {
        try {
            socketConnection.incoming
                .receiveAsFlow()
                .filter { it is Frame.Text }
                .mapNotNull {
                    (it as? Frame.Text)
                        ?.readText()
                        .also {
                            println("receive Message: $it")
                        }
                }
                .reTranslateTo(receiveMessageStateFlow, webSocketScope)
            return Result.success("Success")
        } catch (e: Exception) {
            e.printStackTrace()
            println("error after receiving message: ${e.message}")
            return Result.failure(e)
        }
    }

    override fun observeBirthdayEvent(): Flow<Result<ChildProfile>> {
        return receiveMessageStateFlow.asStateFlow()
            .filter(String::isNotEmpty)
            .map { message ->
                runCatching {
                    Json.decodeFromString<ChildProfileRemote>(message)
                }
            }
            .filter { result ->
                val epochTime = result.getOrNull()?.dob ?: return@filter true
                return@filter verifyDate(epochTime)
            }
            .map { result ->
                result.map(ChildProfileRemote::mapToChildProfile)
            }
    }

    private fun verifyDate(milliseconds: Long): Boolean {
        val currentDate = DateTime.now()
        val checkDate = DateTime(milliseconds)
        val differentMonths = Months.monthsBetween(checkDate, currentDate).months

        return when {
            differentMonths == 0 || differentMonths > NINE_YEARS_IN_MONTH -> {
                false
            }
            differentMonths < 12 -> {
                currentDate.dayOfMonth == checkDate.dayOfMonth
            }
            else -> {
                currentDate.monthOfYear == checkDate.monthOfYear &&
                        currentDate.dayOfMonth == checkDate.dayOfMonth
            }
        }
    }

    override suspend fun disconnectSocket() {
        withContext(Dispatchers.IO) {
            try {
                webSocketSession?.close()
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
        const val PATH_NANIT = "/nanit"
        const val COMMAND_TO_GET_BIRTHDAY = "HappyBirthday"

        const val NINE_YEARS_IN_MONTH = 108
    }
}