package com.bkk.sm.common.kafka.audit

import com.bkk.sm.common.kafka.serializer.ZonedDateTimeSerializer
import com.github.avrokotlin.avro4k.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime
import java.util.UUID

@Serializable
data class AuditLogItem(
    @Serializable(with = UUIDSerializer::class) val id: UUID,
    val type: AuditLogType,
    @Serializable(with = ZonedDateTimeSerializer::class) val timestamp: ZonedDateTime,
    val userId: String,
    val text: String,
)
