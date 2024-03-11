package com.rappi.pay.pse.domain.service

import com.rappi.pay.pse.domain.port.input.PSEPaymentQueryService
import org.springframework.beans.factory.annotation.Value

class PSEPaymentQueryServiceImpl(
    @Value("\${pse-constant.num-payments}") private val numPayments: Long,
) : PSEPaymentQueryService {
    override fun getPayment() {
        println("numPayments = $numPayments")
    }
}
