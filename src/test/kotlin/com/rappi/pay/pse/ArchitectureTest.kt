package com.rappi.pay.pse

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import com.lemonappdev.konsist.api.ext.list.modifierprovider.withModifier
import com.lemonappdev.konsist.api.ext.list.properties
import com.lemonappdev.konsist.api.ext.list.withNameEndingWith
import com.lemonappdev.konsist.api.ext.list.withPackage
import com.lemonappdev.konsist.api.ext.list.withoutName
import com.lemonappdev.konsist.api.verify.assertFalse
import com.lemonappdev.konsist.api.verify.assertTrue
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

    @Test
    fun `output ports should be in domain_port_output`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith("Port")
            .assertTrue { it.resideInPackage("$DOMAIN_PACKAGE.port.output") }
    }

    @Test
    fun `only ports should be in domain_port_output`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withPackage("$DOMAIN_PACKAGE.port.output")
            .assertTrue { it.hasNameEndingWith("Port") }
    }

    @Test
    fun `service interface should be in domain_port_input`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withNameEndingWith("Service")
            .assertTrue { it.resideInPackage("$DOMAIN_PACKAGE.port.input") }
    }

    @Test
    fun `only service interface should be in domain_port_input`() {
        Konsist
            .scopeFromProject()
            .interfaces()
            .withPackage("$DOMAIN_PACKAGE.port.input")
            .assertTrue { it.hasNameEndingWith("Service") }
    }

    @Test
    fun `services implementation should be in domain_service`() {
        Konsist
            .scopeFromProject()
            .classes()
            .withNameEndingWith("ServiceImpl")
            .assertTrue { it.resideInPackage("$DOMAIN_PACKAGE.service") }
    }

    @Test
    fun `only Services implementation should be in domain_service`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withPackage("$DOMAIN_PACKAGE.service")
            .assertTrue { it.hasNameEndingWith("ServiceImpl") }
    }

    /*
        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        var createdAt: LocalDateTime? = null,
        @LastModifiedDate
        @Column(name = "updated_at", nullable = false)
        var updatedAt: LocalDateTime? = null,
     */
    @Test
    fun `Data classes should only use val modifier`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withModifier(KoModifier.DATA) // las classes de config no deberían ser Data
            .properties(includeNested = true, includeLocal = true)
            .withoutName("createdAt", "updatedAt") // cuando usamos @CreatedDate y/o @LastModifiedDate
            .assertTrue { it.hasValModifier }
    }

    /*
    // No usar ApiException en el Domain
    class BadRequestNotAllowedException : CoreException(
        BadRequestNotAllowed.CODE,
        BadRequestNotAllowed.MESSAGE
    )
     */
    @Test
    fun `domain should not use http imports`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("$DOMAIN_PACKAGE..")
            .assertFalse {
                it.hasImport { import -> import.hasNameContaining("http") }
            }
    }

    @Test
    fun `classes in Domain should not have JsonNaming annotation`() {
        Konsist
            .scopeFromProduction()
            .classes()
            .withPackage("$DOMAIN_PACKAGE..")
            .assertFalse {
                it.hasAnnotation { annotation -> annotation.hasNameStartingWith("JsonNaming") }
            }
    }

    @Test
    fun `controllers should not use service directly`() {
        Konsist
            .scopeFromProject()
            .files
            .withPackage("$APPLICATION_PACKAGE..")
            .assertFalse {
                it.hasImport { import -> import.hasNameContaining("$DOMAIN_PACKAGE.service.") }
            }
    }

//    @Test
//    fun `Only models map to entities should be in model_domain`() {
//        val listEntities = mutableListOf<String>()
//        Konsist
//            .scopeFromProject()
//            .classes()
//            .withPackage("$INFRASTRUCTURE_PACKAGE.persistence.entity")
//            .map {
//                listEntities.add(it.name)
//            }
//
//        Konsist
//            .scopeFromProject()
//            .classes()
//            .withPackage("$DOMAIN_PACKAGE.model")
//            .assertTrue {
//                "${it.name}Entity" in listEntities
//            }
//    }
//
//    @Test
//    fun `domain should not use jackson imports`() {
//        Konsist
//            .scopeFromProject()
//            .files
//            .withPackage("$DOMAIN_PACKAGE..")
//            .assertFalse {
//                it.hasImport { import -> import.hasNameContaining("jackson") }
//            }
//    }
//
//    @Test
//    fun `services should have only one public function`() {
//        Konsist
//            .scopeFromProduction()
//            .classes()
//            .withPackage("$DOMAIN_PACKAGE.service")
//            .assertTrue { c -> c.countFunctions { f -> f.hasPublicOrDefaultModifier } == 1 }
//    }
}
