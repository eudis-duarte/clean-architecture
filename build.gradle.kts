plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("com.lemonappdev:konsist:0.13.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}
