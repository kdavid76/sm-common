package com.bkk.sm.common.kafka.notification

import com.github.avrokotlin.avro4k.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
class Notification(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val channel: NotificationChannel,
    val type: NotificationType,
    val parameters: Map<String, String>,
)
