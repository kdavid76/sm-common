package com.bkk.sm.common.errors.responses

data class FieldErrorResource(
    val objectName: String,
    val fieldName: String,
    val errorCodes: List<String?>
)
