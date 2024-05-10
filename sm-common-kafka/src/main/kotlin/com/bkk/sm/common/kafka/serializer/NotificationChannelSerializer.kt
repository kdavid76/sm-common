package com.bkk.sm.common.kafka.serializer

import com.bkk.sm.common.kafka.notification.NotificationChannel
import com.github.avrokotlin.avro4k.decoder.ExtendedDecoder
import com.github.avrokotlin.avro4k.encoder.ExtendedEncoder
import com.github.avrokotlin.avro4k.schema.AvroDescriptor
import com.github.avrokotlin.avro4k.schema.NamingStrategy
import com.github.avrokotlin.avro4k.serializer.AvroSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.modules.SerializersModule
import org.apache.avro.Schema

class NotificationChannelSerializer : AvroSerializer<NotificationChannel>() {

    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor =
        object : AvroDescriptor(NotificationChannel::class, PrimitiveKind.STRING) {
            override fun schema(
                annos: List<Annotation>,
                serializersModule: SerializersModule,
                namingStrategy: NamingStrategy,
            ): Schema = Schema.create(Schema.Type.STRING)
        }

    override fun decodeAvroValue(schema: Schema, decoder: ExtendedDecoder): NotificationChannel {
        return NotificationChannel.valueOf(decoder.decodeString())
    }

    override fun encodeAvroValue(schema: Schema, encoder: ExtendedEncoder, obj: NotificationChannel) {
        encoder.encodeString(obj.name)
    }
}
