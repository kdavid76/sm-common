package com.bkk.sm.common.customer.resources

import com.bkk.sm.common.customer.company.CompanyRole
import java.time.ZonedDateTime

data class UserResource(
    var id: String? = null,
    var username: String,
    var password: String? = null,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var failedLoginAttempts: Int? = 0,
    var roles: MutableList<CompanyRole>? = ArrayList(),
    var registrationTime: ZonedDateTime? = null,
    var lastModificationTime: ZonedDateTime? = null,
    var passwordExpiringEnabled: Boolean = true,
    var passwordExpiryTime: ZonedDateTime? = null,
    var activationKey: String? = null,
    var activatedTime: ZonedDateTime? = null,
    var accountLocked: Boolean = true,
    var enabled: Boolean = false,
    var middleName: String? = null,
)
