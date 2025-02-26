plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.4" //add plugin for ktor
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    gradlePluginPortal()
}

val ktor_version = "2.3.4"

dependencies {
    // Ktor dependencies
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-request-validation:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-gson:$ktor_version")
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")

    // Bcrypt hashing
    implementation("org.mindrot:jbcrypt:0.4")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // Logging
    implementation("ch.qos.logback:logback-classic:1.4.12")

    // Kotlin test dependencies
    testImplementation(kotlin("test"))
}



tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

