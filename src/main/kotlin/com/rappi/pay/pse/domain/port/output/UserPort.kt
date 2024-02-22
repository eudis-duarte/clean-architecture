package com.rappi.pay.pse.domain.port.output

interface UserPort {
    fun getEmail(userId: Long): String
}
