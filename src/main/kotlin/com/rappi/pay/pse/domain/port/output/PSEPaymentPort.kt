package com.rappi.pay.pse.domain.port.output

import com.rappi.pay.pse.domain.model.PSEPayment

interface PSEPaymentPort {
    fun register(psePayment: PSEPayment): String
}
