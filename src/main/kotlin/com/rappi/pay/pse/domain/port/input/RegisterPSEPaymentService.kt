package com.rappi.pay.pse.domain.port.input

import com.rappi.pay.pse.domain.model.PSEPayment

interface RegisterPSEPaymentService {
    fun execute(psePayment: PSEPayment): String
}
