package com.therabbit.room

import com.therabbit.data.MessageDataSource
import com.therabbit.data.model.Message
import io.ktor.http.cio.websocket.*
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(private val messageDataSource: MessageDataSource) {
    private val members = ConcurrentHashMap<String, Member>()

    fun toJoin(userName: String, sessionId: String, socket: WebSocketSession) {
        if (members.containsKey(userName)) {
            throw MemberAlreadyExistsException()
        }
        members[userName] = Member(userName, sessionId, socket)
    }

    suspend fun sendMessage(senderUserName: String, message: String) {
        members.values.forEach { member ->
            val messageEntity =
                Message(text = message, userName = senderUserName, timestamp = System.currentTimeMillis())
            messageDataSource.insert(messageEntity)

            val parsedMessage = Json.encodeToString(Message.serializer(), messageEntity)
            member.socket.send(Frame.Text(parsedMessage))
        }
    }

    suspend fun getAllMessage(): List<Message> {
        return messageDataSource.getAll()
    }

    suspend fun tryDisconnect(userName: String) {
        members[userName]?.socket?.close()
        if (members.containsKey(userName)) {
            members.remove(userName)
        }
    }
}