package com.rappi.pay.pse.domain.port.output

interface AccountPort {
    fun getUserId(accountId: Long): Long
}
