package com.bkk.sm.common.customer.resources

import com.bkk.sm.common.model.Address
import java.util.*

data class CompanyResource(
    var id: String?,
    var code: String,
    var name: String,
    var email: String,
    var address: Address,
    var taxId: String? = "",
    var bankAccountNumber: String? = "",
    var optionalContactInfo: String? = "",
    var activationKey: String? = "",
    var activationTime: Date?,
    var registrationTime: Date?,
    var lastModificationTime: Date?,
    var enabled: Boolean? = false,
    var version: Long = 0,
)
