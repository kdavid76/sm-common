package com.bkk.sm.common.customer.validators

import com.bkk.sm.common.customer.company.CompanyRole
import com.bkk.sm.common.model.AreaType
import com.bkk.sm.common.model.Roles
import com.bkk.sm.common.utils.CommonResourceTestUtils
import com.bkk.sm.common.customer.resources.CompanyWithAdminResource
import io.kotest.matchers.shouldBe
import io.mockk.called
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.context.ActiveProfiles
import org.springframework.validation.BeanPropertyBindingResult
import java.time.LocalDateTime
import java.util.*

@ActiveProfiles("test")
class CompanyWithAdminResourceValidatorTest {
    private val companyResourceValidatorMock = mockk<CompanyResourceValidator>()
    private val userResourceValidatorMock = mockk<UserResourceValidator>()
    private val validator = CompanyWithAdminResourceValidator(companyResourceValidatorMock, userResourceValidatorMock)

    private val bkk = CommonResourceTestUtils.createCompanyResource(
        UUID.randomUUID().toString(), "bkk", "Beszterce KK",
        "besztercekk@email.com", "12345678-1-11", "11111111-22222222-33333333",
        "", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), true,
        1, CommonResourceTestUtils.createAddress("Salgotarjan", 3100, "Medves", AreaType.KORUT,
            "86", 7, 40, null)
    )
    private val davidk = CommonResourceTestUtils.createUserResource("1", "davidk", "password",
                            "Krisztian", "David", "davidkrisztian@email.com",
                            mutableListOf(CompanyRole(Roles.ROLE_ADMIN, "bkk")))
    @BeforeEach
    fun initialise() {
        clearMocks(companyResourceValidatorMock, userResourceValidatorMock)
    }

    @Test
    fun `Check supporting the right class`() {

        validator.supports(CompanyWithAdminResource::class.java) shouldBe true
        validator.supports(String::class.java) shouldBe false
    }

    @Test
    fun `Check if company validator called`() {
        // given
        val resourceMock = mockk<CompanyWithAdminResource>()
        val errorsMock = mockk<BeanPropertyBindingResult>()
        every { resourceMock.companyResource } answers { bkk }
        every { resourceMock.userResource } answers { null }
        every { companyResourceValidatorMock.validate(any(), any()) } answers { }
        every { errorsMock.addAllErrors(any()) } answers { }

        // when
        validator.validate(resourceMock, errorsMock)

        // then
        verify {
            userResourceValidatorMock wasNot called
        }
    }

    @Test
    fun `Check if user validator called`() {
        // given
        val resourceMock = mockk<CompanyWithAdminResource>()
        val errorsMock = mockk<BeanPropertyBindingResult>()
        every { resourceMock.companyResource } answers { bkk }
        every { resourceMock.userResource } answers { davidk }
        every { companyResourceValidatorMock.validate(any(), any()) } answers { }
        every { userResourceValidatorMock.validate(any(), any()) } answers { }
        every { errorsMock.addAllErrors(any()) } answers { }

        // when
        validator.validate(resourceMock, errorsMock)

        // then
        verify(exactly = 1) { userResourceValidatorMock.validate(any(), any()) }
        verify(exactly = 1) { companyResourceValidatorMock.validate(any(), any()) }
    }
}