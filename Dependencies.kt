//
//object Versions {
//    const val kotlinUuid = "0.0.17"
//    const val postgres = "42.5.4"
//    const val cache4k = "0.9.0"
//
//    object KotlinX {
//        const val serializationJson = "1.4.1"
//        const val coroutinesCore = "1.6.4"
//        const val datetime = "0.4.0"
//    }
//
//    object Arrow {
//        const val GENERAL = "1.1.2"
//    }
//
//    object Ktor {
//        const val GENERAL = "2.3.0"
//    }
//
//    object Koin {
//        const val GENERAL = "3.3.3"
//
//        const val koinAnnotations = "1.1.1"
//    }
//
//    object Exposed {
//        const val GENERAL = "0.40.1"
//    }
//
//}f
//
//
//open class DependencyContainer(
//    protected val group: String,
//    protected val version: String? = null
//) {
//    fun dependency(artifact: String, version: String? = null): String =
//        dependency(group = group, artifact = artifact, version = version ?: this.version ?: "")
//}
//
//fun dependency(group: String, artifact: String, version: String) = "$group:$artifact:$version"
//
//object Dependencies {
//    val KotlinUuid = dependency(group = "app.softwork", artifact = "kotlinx-uuid-core", version = Versions.kotlinUuid)
//    val Postgres = dependency(group = "org.postgresql", artifact = "postgresql", version = Versions.postgres)
//    val Cache4k = dependency(group = "io.github.reactivecircus.cache4k", artifact = "cache4k", version = Versions.cache4k)
//
//    object Logback: DependencyContainer(group = "ch.qos.logback", version = Versions.Logback.GENERAL) {
//        val Classic = dependency(artifact = "logback-classic")
//    }
//
//
//    object KotlinX: DependencyContainer(group = "org.jetbrains.kotlinx") {
//        val SerializationJson = dependency(artifact = "kotlinx-serialization-json", version = Versions.KotlinX.serializationJson)
//        val CoroutinesCore = dependency(artifact = "kotlinx-coroutines-core", version = Versions.KotlinX.coroutinesCore)
//        val Datetime = dependency(artifact = "kotlinx-datetime", version = Versions.KotlinX.datetime)
//    }
//
//    object Arrow: DependencyContainer( group ="io.arrow-kt", version = Versions.Arrow.GENERAL) {
//        val Core = dependency(artifact = "arrow-core")
//        val FxCoroutines = dependency(artifact = "arrow-fx-coroutines")
//    }
//
//    object Ktor: DependencyContainer(group = "io.ktor", version = Versions.Ktor.GENERAL) {
//        val Resources = dependency(artifact = "ktor-resources")
//        val SerializationKotlinXJson = dependency(artifact = "ktor-serialization-kotlinx-json")
//
//        object Server: DependencyContainer(group = group, version = version) {
//
//            val Core = dependency(artifact = "ktor-server-core")
//            val Netty = dependency(artifact = "ktor-server-netty")
//            val Resources = dependency(artifact = "ktor-server-resources")
//            val StatusPages = dependency(artifact = "ktor-server-status-pages")
//        }
//
//        object Client: DependencyContainer(group = group, version = version) {
//            val Core = dependency(artifact = "ktor-client-core")
//            val Cio = dependency(artifact = "ktor-client-cio")
//            val Resources = dependency(artifact = "ktor-client-resources")
//            val ContentNegotiation = dependency(artifact = "ktor-client-content-negotiation")
//        }
//    }
//
//    object Koin: DependencyContainer(group = "io.insert-koin", version = Versions.Koin.GENERAL) {
//        val Core = dependency(artifact = "koin-core")
//        val Annotation = dependency(artifact = "koin-annotations", version = Versions.Koin.koinAnnotations)
//        val KspCompiler = dependency(artifact = "koin-ksp-compiler", version = Versions.Koin.koinAnnotations)
//    }
//
//    object Exposed: DependencyContainer(group = "org.jetbrains.exposed", version = Versions.Exposed.GENERAL) {
//        val Core = dependency(artifact = "exposed-core")
//        val Dao = dependency(artifact = "exposed-dao")
//        val Jdbc = dependency(artifact = "exposed-jdbc")
//        val KotlinDatetime = dependency(artifact = "exposed-kotlin-datetime")
//    }
//}