package com.therabbit.plugins

import com.therabbit.room.RoomController
import com.therabbit.routes.chatSocket
import com.therabbit.routes.getAllMessage
import io.ktor.application.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        chatSocket(roomController)
        getAllMessage(roomController)
    }
}
