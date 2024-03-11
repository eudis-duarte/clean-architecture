package com.rappi.pay.pse.infrastructure.common.config

import com.rappi.pay.pse.domain.port.output.AccountPort
import com.rappi.pay.pse.domain.port.output.CachePort
import com.rappi.pay.pse.domain.port.output.NotificationPort
import com.rappi.pay.pse.domain.port.output.PSEPaymentPort
import com.rappi.pay.pse.domain.port.output.UserPort
import com.rappi.pay.pse.domain.service.PSEPaymentQueryServiceImpl
import com.rappi.pay.pse.domain.service.RegisterPSEPaymentServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ServiceBeanConfig {
    @Bean
    fun buildPSEPaymentQueryService(numPayments: Long) = PSEPaymentQueryServiceImpl(numPayments)

    @Bean
    fun buildRegisterPSEPaymentService(
        psePaymentPort: PSEPaymentPort,
        accountPort: AccountPort,
        userPort: UserPort,
        notificationPort: NotificationPort,
        cachePort: CachePort,
    ) = RegisterPSEPaymentServiceImpl(
        psePaymentPort,
        accountPort,
        userPort,
        notificationPort,
        cachePort,
    )
}
