import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.georgeii"
version = "1.0"

plugins {
    kotlin("jvm") version "1.5.10"

    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}
