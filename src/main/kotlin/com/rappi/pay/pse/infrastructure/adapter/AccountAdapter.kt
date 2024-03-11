package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.port.output.AccountPort
import org.springframework.stereotype.Component

@Component
class AccountAdapter : AccountPort {
    override fun getUserId(accountId: Long): Long {
        TODO("Not yet implemented")
    }
}
