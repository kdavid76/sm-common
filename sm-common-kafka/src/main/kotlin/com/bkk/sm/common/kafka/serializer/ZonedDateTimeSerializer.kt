package com.bkk.sm.common.kafka.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.ZoneId
import java.time.ZonedDateTime

object ZonedDateTimeSerializer : KSerializer<ZonedDateTime> {
    override val descriptor: SerialDescriptor = ZonedDateTimeSurrogate.serializer().descriptor

    override fun deserialize(decoder: Decoder): ZonedDateTime {
        val surrogate = decoder.decodeSerializableValue(ZonedDateTimeSurrogate.serializer())
        return ZonedDateTime.of(surrogate.localDateTime, ZoneId.of(surrogate.zoneId))
    }

    override fun serialize(encoder: Encoder, value: ZonedDateTime) {
        val surrogate = ZonedDateTimeSurrogate(value.toLocalDateTime(), value.zone.id)
        encoder.encodeSerializableValue(ZonedDateTimeSurrogate.serializer(), surrogate)
    }
}
