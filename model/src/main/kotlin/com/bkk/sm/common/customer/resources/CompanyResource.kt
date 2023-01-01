package com.bkk.sm.common.customer.resources

import com.bkk.sm.common.model.Address
import java.time.LocalDateTime

data class CompanyResource(
    var id: String?,
    var code: String,
    var name: String,
    var email: String,
    var address: Address,
    var taxId: String? = "",
    var bankAccountNumber: String? = "",
    var optionalContactInfo: String? = "",
    var activationToken: String? = "",
    var activationTime: LocalDateTime?,
    var registrationTime: LocalDateTime?,
    var lastModificationTime: LocalDateTime?,
    var enabled: Boolean? = false,
    var version: Long = 0
)
