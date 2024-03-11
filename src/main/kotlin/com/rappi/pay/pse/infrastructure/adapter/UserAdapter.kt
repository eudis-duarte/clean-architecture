package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.port.output.UserPort
import org.springframework.stereotype.Component

@Component
class UserAdapter : UserPort {
    override fun getEmail(userId: Long): String {
        TODO("Not yet implemented")
    }
}
