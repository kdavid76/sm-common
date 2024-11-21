package com.bkk.sm.common.customer.resources

import com.bkk.sm.common.model.Address
import java.time.ZonedDateTime

data class CompanyResource(
    var id: String? = null,
    var code: String,
    var name: String,
    var email: String,
    var address: Address,
    var taxId: String? = "",
    var bankAccountNumber: String? = "",
    var optionalContactInfo: String? = "",
    var activationKey: String? = "",
    var activationTime: ZonedDateTime? = null,
    var registrationTime: ZonedDateTime? = ZonedDateTime.now(),
    var lastModificationTime: ZonedDateTime? = ZonedDateTime.now(),
    var enabled: Boolean? = false,
    var version: Long = 0,
)
