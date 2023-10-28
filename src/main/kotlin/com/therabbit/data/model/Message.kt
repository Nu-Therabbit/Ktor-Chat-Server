package com.therabbit.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Message(
    @BsonId
    val id: String = ObjectId.get().toString(),
    val text: String,
    val userName: String,
    val timestamp: Long,
)
