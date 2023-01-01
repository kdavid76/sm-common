package com.bkk.sm.common.errors.responses

import org.springframework.validation.Errors
import org.springframework.validation.FieldError

data class FormErrorResource(
    val objectName: String?,
    var fieldErrors: MutableList<FieldErrorResource>? = mutableListOf()
) {
    fun addFieldError(fieldErrorResource: FieldErrorResource) = fieldErrors?.add(fieldErrorResource)

    data class Builder(
        var objectName: String? = "",
        var fieldErrors: MutableList<FieldErrorResource>? = mutableListOf()
    ) {
        fun objectName(objectName: String) = apply { this.objectName = objectName }
        fun fieldErrors(fieldErrors: MutableList<FieldErrorResource>) = apply { this.fieldErrors = fieldErrors }
        fun addFieldError(fieldErrorResource: FieldErrorResource): Builder {
            fieldErrors?.add(fieldErrorResource)
            return this
        }
        fun addFieldErrors(errors: Errors): Builder {
            errors.allErrors.forEach {
                val error = it as FieldError
                this.fieldErrors?.add(
                    FieldErrorResource(
                        objectName = error.objectName,
                        fieldName = error.field,
                        errorCodes = listOf(error.codes?.get(error.codes!!.size - 1))
                    )
                )
            }
            return this
        }
        fun build() = FormErrorResource(objectName, fieldErrors)
    }
}
