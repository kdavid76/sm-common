package com.bkk.sm.common.customer.resources

data class CompanyAndUserResource(
    var companyResource: CompanyResource,
    var userResource: UserResource?,
    var isNewUser: Boolean = false,
)
