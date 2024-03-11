plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.lemonappdev:konsist:0.13.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

repositories {
    mavenCentral()
    maven(url = "https://repo.spring.io/snapshot")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
