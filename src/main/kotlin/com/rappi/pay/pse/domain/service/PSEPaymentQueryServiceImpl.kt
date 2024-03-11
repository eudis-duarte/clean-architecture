package com.rappi.pay.pse.domain.service

import com.rappi.pay.pse.domain.port.input.PSEPaymentQueryService
import com.rappi.pay.pse.domain.port.output.ConfigValuePort

class PSEPaymentQueryServiceImpl(
    private val configValuePort: ConfigValuePort,
) : PSEPaymentQueryService {
    override fun getPayment() {
        println("numPayments = ${configValuePort.getNumPayments()}")
    }
}
