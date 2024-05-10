package com.bkk.sm.common.kafka

enum class KafkaTopics(val topicName: String) {
    AUDIT_LOG("sm-audit-log"), NOTIFICATIONS("sm-notifications")
}
