package com.rappi.pay.pse.infrastructure.common.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "pse-constant")
class PSEConfig {
    var numPayments: Long = 0
}
