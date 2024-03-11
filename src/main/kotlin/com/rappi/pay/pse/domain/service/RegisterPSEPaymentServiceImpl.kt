package com.rappi.pay.pse.domain.service

import com.rappi.pay.pse.domain.model.PSEPayment
import com.rappi.pay.pse.domain.port.input.RegisterPSEPaymentService
import com.rappi.pay.pse.domain.port.output.AccountPort
import com.rappi.pay.pse.domain.port.output.CachePort
import com.rappi.pay.pse.domain.port.output.NotificationPort
import com.rappi.pay.pse.domain.port.output.PSEPaymentPort
import com.rappi.pay.pse.domain.port.output.UserPort

class RegisterPSEPaymentServiceImpl(
    private val psePaymentPort: PSEPaymentPort,
    private val accountPort: AccountPort,
    private val userPort: UserPort,
    private val notificationPort: NotificationPort,
    private val cachePort: CachePort,
) : RegisterPSEPaymentService {
    override fun execute(psePayment: PSEPayment): String {
        // Con el id de la cuenta consultar el id del usuario
        val userId = accountPort.getUserId(psePayment.accountId)
        // salvar el userId en caché
        cachePort.saveUserId(userId)
        // Con el id del usuario consultar su email
        val email = userPort.getEmail(userId)
        // Registrar el pago en la BD
        val transactionId = psePaymentPort.register(psePayment)
        // Enviar notificación del pago al correo
        notificationPort.sendEmail(
            email,
            subject = "pago por PSE",
            body = "usted realizó un pago con transacción No. $transactionId",
        )
        return transactionId
    }
}
