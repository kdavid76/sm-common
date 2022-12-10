package com.bkk.sm.common.customer.resources

data class CompanyWithAdminResource(
        var companyResource: CompanyResource,
        var userResource: UserResource?
)