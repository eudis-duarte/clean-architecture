package com.rappi.pay.pse.infrastructure.adapter

import com.rappi.pay.pse.domain.port.output.ConfigValuePort
import com.rappi.pay.pse.infrastructure.common.config.PSEConfig
import org.springframework.stereotype.Component

@Component
class ConfigValueAdapter(
    private val pseConfig: PSEConfig,
) : ConfigValuePort {
    override fun getNumPayments() = pseConfig.numPayments
    // ayuda mucho cuando manejamos listas en secrets
}
