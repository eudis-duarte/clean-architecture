package com.rappi.pay.pse

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.jupiter.api.Test

private const val DOMAIN_PACKAGE = "com.rappi.pay.pse.domain"
private const val APPLICATION_PACKAGE = "com.rappi.pay.pse.application"
private const val INFRASTRUCTURE_PACKAGE = "com.rappi.pay.pse.infrastructure"

class ArchitectureTest {
    @Test
    fun `clean architecture layers should have correct dependencies`() {
        Konsist
            .scopeFromProject()
            .assertArchitecture {
                // Define layers
                val domain = Layer("Domain", "$DOMAIN_PACKAGE..")
                val application = Layer("Application", "$APPLICATION_PACKAGE..")
                val infrastructure = Layer("Infrastructure", "$INFRASTRUCTURE_PACKAGE..")

                // Define architecture assertions
                domain.dependsOnNothing()
                application.dependsOn(domain)
                infrastructure.dependsOn(domain)
            }
    }

    @Test
    fun `domain should not use spring_framework`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("$DOMAIN_PACKAGE..")
            .assertFalse {
                it.hasImport { import -> import.hasNameStartingWith("org.springframework") }
            }
    }
}
