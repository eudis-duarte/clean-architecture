package com.rappi.pay.pse.domain.service

import com.rappi.pay.pse.domain.model.PSEPayment
import com.rappi.pay.pse.domain.port.output.AccountPort
import com.rappi.pay.pse.domain.port.output.CachePort
import com.rappi.pay.pse.domain.port.output.NotificationPort
import com.rappi.pay.pse.domain.port.output.PSEPaymentPort
import com.rappi.pay.pse.domain.port.output.UserPort
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime
import java.util.UUID

@ExtendWith(MockKExtension::class)
class RegisterPSEPaymentServiceImplTest {
    @MockK
    private lateinit var psePaymentPort: PSEPaymentPort

    @MockK
    private lateinit var accountPort: AccountPort

    @MockK
    private lateinit var userPort: UserPort

    @MockK
    private lateinit var notificationPort: NotificationPort

    @MockK
    private lateinit var cachePort: CachePort

    @InjectMockKs
    private lateinit var registerPSEPaymentServiceImpl: RegisterPSEPaymentServiceImpl

    @Test
    fun `should execute successful when all data is sent`() {
        // Arrange
        val transactionId = UUID.randomUUID().toString()
        val accountId = 123456L
        val userId = 235689L
        val email = "good@mail.com"
        val subject = "pago por PSE"
        val body = "usted realizó un pago con transacción No. $transactionId"
        val psePayment =
            PSEPayment(
                accountId = accountId,
                amount = 5_000_000.25,
                businessName = "business",
                purchaseDescription = "a purchase",
                date = LocalDateTime.now(),
            )
        every { psePaymentPort.register(psePayment) } answers { transactionId }
        every { accountPort.getUserId(accountId) } answers { userId }
        every { userPort.getEmail(userId) } answers { email }
        every { notificationPort.sendEmail(email, subject, body) } answers { }
        every { cachePort.saveUserId(userId) } answers { }

        // Act
        val response = registerPSEPaymentServiceImpl.execute(psePayment)

        // Assert
        assertEquals(transactionId, response)
        verify(exactly = 1) { psePaymentPort.register(any()) }
        verify(exactly = 1) { accountPort.getUserId(any()) }
        verify(exactly = 1) { userPort.getEmail(any()) }
        verify(exactly = 1) { notificationPort.sendEmail(any(), any(), any()) }
        verify(exactly = 1) { cachePort.saveUserId(any()) }
    }
}
