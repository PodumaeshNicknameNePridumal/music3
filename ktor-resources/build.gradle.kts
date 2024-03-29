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


    implementation("app.softwork:kotlinx-uuid-core:${Versions.kotlinUuid}")
    implementation("org.postgresql:postgresql:${Versions.postgres}")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KotlinX.serializationJson}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KotlinX.coroutinesCore}")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KotlinX.datetime}")

    implementation("io.ktor:ktor-resources:${Versions.Ktor.GENERAL}")
    implementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor.GENERAL}")

    implementation("io.ktor:ktor-server-core:${Versions.Ktor.GENERAL}")
    implementation("io.ktor:ktor-server-netty:${Versions.Ktor.GENERAL}")
    implementation("io.ktor:ktor-server-resources:${Versions.Ktor.GENERAL}")
    implementation("io.ktor:ktor-server-status-pages:${Versions.Ktor.GENERAL}")

    implementation("io.insert-koin:koin-core:${Versions.Koin.GENERAL}")
    implementation("io.insert-koin:koin-annotations:${Versions.Koin.koinAnnotations}")
    ksp("io.insert-koin:koin-ksp-compiler:${Versions.Koin.koinAnnotations}")

    implementation("org.jetbrains.exposed:exposed-core:${Versions.Exposed.GENERAL}")
    implementation("org.jetbrains.exposed:exposed-dao:${Versions.Exposed.GENERAL}")
    implementation("org.jetbrains.exposed:exposed-jdbc:${Versions.Exposed.GENERAL}")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:${Versions.Exposed.GENERAL}")


    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")
    implementation("io.ktor:ktor-server-resources:${Versions.Ktor.GENERAL}")


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