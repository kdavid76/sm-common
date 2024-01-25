package com.bkk.sm.common.kafka.notification

import java.util.*

// @OptIn(ExperimentalSerializationApi::class)
// @Serializer(forClass = NotificationChannelSerializer::class)
data class Notification(
    // @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    // @Serializable(with = NotificationChannelSerializer::class)
    val channel: NotificationChannel,
    val type: NotificationType,
    val parameters: Map<String, String>,
)
