package main
import database.DatabaseConnection
import database.DatabaseModule
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import main.handlers.HandlerModule
import main.handlers.TrackHandler
import managers.ManagerModule
import mappers.MappersModule
import org.koin.java.KoinJavaComponent
import org.koin.ksp.generated.module
import repositories.RepositoryModule


fun main(args: Array<String>) {
    startKoin()
    databaseConnect()
}

fun startKoin() {
    org.koin.core.context.startKoin {
        modules(DatabaseModule().module,MappersModule().module,RepositoryModule().module, ManagerModule().module, HandlerModule().module)
    }
}

fun databaseConnect() {
    val connection = DatabaseConnection()
    connection.connect()
    startService()
}

fun startService() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json(
                Json {
                    encodeDefaults = false
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        install(StatusPages) {
            exception<Throwable> { call, cause ->
                call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
            }
        }
        install(Resources)
        install(CallLogging)
        routing {
            with(KoinJavaComponent.getKoin().get<TrackHandler>()) {
                configureTracksRoutes()
            }
        }
    }.start(wait = true)
}