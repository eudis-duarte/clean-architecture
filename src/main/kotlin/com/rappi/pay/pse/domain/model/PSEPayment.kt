package com.rappi.pay.pse.domain.model

import java.time.LocalDateTime

data class PSEPayment(
    val accountId: Long,
    val amount: Double,
    val businessName: String,
    val purchaseDescription: String,
    val date: LocalDateTime,
)
