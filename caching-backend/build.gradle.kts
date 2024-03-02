plugins {
    kotlin("jvm") version "1.8.10"
    application
    kotlin("plugin.serialization") version "1.8.10"
    id("com.google.devtools.ksp") version "1.8.10-1.0.9"

}

group = "org.example"
version = "1.0-SNAPSHOT"

object Versions {
    const val kotlinUuid = "0.0.17"
    const val postgres = "42.5.4"
    const val cache4k = "0.9.0"

    object KotlinX {
        const val serializationJson = "1.4.1"
        const val coroutinesCore = "1.6.4"
        const val datetime = "0.4.0"
    }

    object Arrow {
        const val GENERAL = "1.1.2"
    }

    object Ktor {
        const val GENERAL = "2.3.0"
    }

    object Koin {
        const val GENERAL = "3.3.3"

        const val koinAnnotations = "1.1.1"
    }

    object Exposed {
        const val GENERAL = "0.40.1"
    }

}
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {

    implementation(project(":models"))
    implementation(project(":exposed-backend"))
//    implementation(project(":server"))
    testImplementation(kotlin("test"))

    implementation("io.github.reactivecircus.cache4k:cache4k:${Versions.cache4k}")
    implementation("io.insert-koin:koin-core:${Versions.Koin.GENERAL}")
    implementation("io.insert-koin:koin-annotations:${Versions.Koin.koinAnnotations}")
    ksp("io.insert-koin:koin-ksp-compiler:${Versions.Koin.koinAnnotations}")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.10-1.0.9")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

sourceSets.main {
    kotlin.srcDir("build/generated/ksp/main/kotlin")
}

application {
    mainClass.set("MainKt")
}