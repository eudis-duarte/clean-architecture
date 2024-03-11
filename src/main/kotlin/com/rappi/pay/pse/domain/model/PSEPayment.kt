package com.rappi.pay.pse.domain.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class PSEPayment(
    val accountId: Long,
    val amount: Double,
    val businessName: String,
    val purchaseDescription: String,
    val date: LocalDateTime,
)
