package com.bkk.sm.common.customer.validators

import com.bkk.sm.common.customer.resources.CompanyResource
import com.bkk.sm.common.model.Address
import com.bkk.sm.common.model.AreaType
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.test.context.ActiveProfiles
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors
import java.time.Instant
import java.util.*

@ActiveProfiles("test")
class CompanyResourceValidatorTest {

    private val validator = CompanyResourceValidator()

    @Test
    fun `Check supporting the right class`() {
        Assertions.assertThat(validator.supports(CompanyResource::class.java)).isTrue
        Assertions.assertThat(validator.supports(String::class.java)).isFalse
    }

    @Test
    fun `Verifying valid resource`() {
        val companyResource = CompanyResource(
            id = "123456789",
            name = "Beszterce KK",
            code = "bkk",
            email = "bkk@gmail.com",
            address = Address(
                postCode = 3100,
                city = "Salgotarjan",
                areaType = AreaType.KORUT,
                streetName = "Medves",
                houseNumber = "86",
                level = 7,
                door = 40,
                locationCode = null,
            ),
            activationTime = Date.from(Instant.now()),
            registrationTime = Date.from(Instant.now()),
            lastModificationTime = Date.from(Instant.now()),
        )

        val errors: Errors = BeanPropertyBindingResult(companyResource, CompanyResource::class.java.name)

        validator.validate(companyResource, errors)
        Assertions.assertThat(errors.errorCount).isEqualTo(0)
    }

    @Test
    fun `Verifying invalid resource`() {
        val companyResource = CompanyResource(
            id = "123456789",
            name = " ",
            code = " ",
            email = "bkkgmail.com",
            address = Address(
                postCode = 0,
                city = " ",
                areaType = AreaType.KORUT,
                streetName = " ",
                houseNumber = " ",
                level = null,
                door = null,
                locationCode = null,
            ),
            activationTime = Date.from(Instant.now()),
            registrationTime = Date.from(Instant.now()),
            lastModificationTime = Date.from(Instant.now()),
        )

        val errors: Errors = BeanPropertyBindingResult(companyResource, CompanyResource::class.java.name)

        validator.validate(companyResource, errors)
        Assertions.assertThat(errors.errorCount).isEqualTo(6)
    }
}
