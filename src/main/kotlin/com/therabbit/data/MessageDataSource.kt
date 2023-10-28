package com.therabbit.data

import com.therabbit.data.model.Message

interface MessageDataSource {
    suspend fun getAll(): List<Message>
    suspend fun insert(message: Message)
}