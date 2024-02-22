package com.rappi.pay.pse.domain.port.output

interface NotificationPort {
    fun sendEmail(
        email: String,
        subject: String,
        body: String,
    )
}
