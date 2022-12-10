package com.bkk.sm.common.customer.resources

data class ActivateUserResource(
    val username: String,
    val activationCode: String
)