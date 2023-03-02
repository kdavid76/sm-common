package com.bkk.sm.common.model

data class Address(
    val city: String,
    val postCode: Int,
    val streetName: String,
    val areaType: AreaType,
    val houseNumber: String,
    val level: Int?,
    val door: Int?,
    val locationCode: String?,
)
