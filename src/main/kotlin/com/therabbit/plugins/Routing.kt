package com.therabbit.plugins

import com.therabbit.room.RoomController
import com.therabbit.routes.chatSocket
import com.therabbit.routes.getAllMessage
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        get("/") {
            call.respond("Hello...")
        }
        chatSocket(roomController)
        getAllMessage(roomController)
    }
}
