package com.bkk.sm.common.kafka.serializer

import com.github.avrokotlin.avro4k.serializer.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@SerialName("ZonedDateTime")
class ZonedDateTimeSurrogate(@Serializable(with = LocalDateTimeSerializer::class) val localDateTime: LocalDateTime, val zoneId: String)
