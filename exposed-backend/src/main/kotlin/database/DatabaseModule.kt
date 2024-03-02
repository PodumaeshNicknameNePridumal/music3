package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import database.tables.Genres
import database.tables.TrackToGenre
import database.tables.Tracks
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Single
import javax.sql.DataSource

@org.koin.core.annotation.Module
@ComponentScan()
class DatabaseModule


interface IDatabaseConnection {
    fun connect(): Database?
}

@Single
open class DatabaseConnection(
    private val failure: Throwable.() -> Unit = {},
    private val success: (Database) -> Unit = {}
) : IDatabaseConnection {

    private var database: Database? = null
    private val jdbcURL = "jdbc:postgresql://localhost:5432/postgres"
    private val user = "postgres"
    private val pass = "12345"
    private val driver = "org.postgresql.Driver"

    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = jdbcURL
        username = user
        password = pass
        driverClassName = driver
        maximumPoolSize = 10

    }


    private val dataSource: DataSource = HikariDataSource(hikariConfig)

    override fun connect(): Database? {
        runCatching {
            val dataSource = HikariDataSource(hikariConfig)
            database = Database.connect(dataSource)

            // Создание таблицы Users при подключении
            transaction {
                SchemaUtils.create(Tracks, Genres, TrackToGenre)
                addLogger(StdOutSqlLogger)

            }

        }.onSuccess {
            success(database!!)
        }.onFailure {
            it.failure()
        }

        return database
    }
}