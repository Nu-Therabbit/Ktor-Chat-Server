package com.therabbit.di

import com.therabbit.data.MessageDataSource
import com.therabbit.data.MessageDataSourceImpl
import com.therabbit.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient()
            .coroutine
            .getDatabase("message_db_yt")
    }
    single<MessageDataSource> { MessageDataSourceImpl(get()) }
    single { RoomController(get()) }
}