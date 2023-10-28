package com.therabbit.plugins

import com.therabbit.session.ChatSession
import io.ktor.application.*
import io.ktor.sessions.*
import io.ktor.util.*

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<ChatSession>("SESSION")
    }

    intercept(ApplicationCallPipeline.Features) {
        if (call.sessions.get<ChatSession>() == null) {
            val userName = call.parameters["userName"] ?: "Guest"
            call.sessions.set(ChatSession(userName, generateNonce()))
        }
    }
}
