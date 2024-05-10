package com.bkk.sm.common.kafka.audit

import java.time.ZonedDateTime
import java.util.*

// @Serializable
data class AuditLogItem(
    // @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val type: AuditLogType,
    // @Serializable(with = ZonedDateTimeSerializer::class)
    val timestamp: ZonedDateTime,
    val userId: String,
    val text: String,
)
