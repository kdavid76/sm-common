package com.bkk.sm.common.utils

import com.bkk.sm.common.customer.company.CompanyRole
import com.bkk.sm.common.customer.resources.CompanyResource
import com.bkk.sm.common.customer.resources.UserResource
import com.bkk.sm.common.model.Address
import com.bkk.sm.common.model.AreaType
import java.time.LocalDateTime

class CommonResourceTestUtils {

    companion object {
        fun createUserResource(
            id: String,
            username: String,
            password: String,
            firstName: String,
            lastName: String,
            email: String,
            roles: MutableList<CompanyRole>
        ) = UserResource(
            id = id,
            username = username,
            password = password,
            firstName = firstName,
            lastName = lastName,
            email = email,
            roles = roles
        )

        fun createAddress(
            city: String,
            postCode: Int,
            streetName: String,
            areaType: AreaType,
            houseNumber: String,
            level: Int?,
            door: Int?,
            locationCode: String?
        ) = Address(
            city = city,
            postCode = postCode,
            streetName = streetName,
            areaType = areaType,
            houseNumber = houseNumber,
            level = level,
            door = door,
            locationCode = locationCode
        )

        fun createCompanyResource(
            id: String?,
            code: String,
            name: String,
            email: String,
            taxId: String?,
            bankAccountNumber: String?,
            activationToken: String?,
            activationTime: LocalDateTime?,
            registrationTime: LocalDateTime?,
            lastModificationTime: LocalDateTime?,
            enabled: Boolean?,
            version: Long,
            address: Address
        ) = CompanyResource(
            id = id, code = code, name = name, email = email, taxId = taxId,
            bankAccountNumber = bankAccountNumber, activationToken = activationToken,
            activationTime = activationTime, registrationTime = registrationTime,
            lastModificationTime = lastModificationTime, enabled = enabled,
            version = version, address = address
        )
    }
}
