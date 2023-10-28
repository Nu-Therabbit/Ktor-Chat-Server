package com.therabbit.room

import io.ktor.http.cio.websocket.*

data class Member(
    val userName: String,
    val sessionId: String,
    val socket: WebSocketSession,
)
