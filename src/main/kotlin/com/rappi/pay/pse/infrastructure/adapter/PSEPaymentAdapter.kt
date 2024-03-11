package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.model.PSEPayment
import com.rappi.pay.pse.domain.port.output.PSEPaymentPort
import org.springframework.stereotype.Component

@Component
class PSEPaymentAdapter : PSEPaymentPort {
    override fun register(psePayment: PSEPayment): String {
        TODO("Not yet implemented")
    }
}
