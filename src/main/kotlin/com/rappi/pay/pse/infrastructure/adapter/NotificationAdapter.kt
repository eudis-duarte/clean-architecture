package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.port.output.NotificationPort
import org.springframework.stereotype.Component

@Component
class NotificationAdapter : NotificationPort {
    override fun sendEmail(
        email: String,
        subject: String,
        body: String,
    ) {
        TODO("Not yet implemented")
    }
}
