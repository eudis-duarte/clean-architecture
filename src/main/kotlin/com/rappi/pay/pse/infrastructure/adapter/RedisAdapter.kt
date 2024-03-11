package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.port.output.CachePort

class RedisAdapter : CachePort {
    override fun saveUserId(userId: Long) {
        println("userId $userId saved")
    }
}
