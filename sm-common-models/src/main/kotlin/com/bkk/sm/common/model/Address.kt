package com.bkk.sm.common.model

data class Address(
    val city: String,
    val postCode: Int,
    val streetName: String,
    val areaType: AreaType,
    val houseNumber: String,
    val level: Int? = null,
    val door: Int? = null,
    val locationCode: String? = null,
)
