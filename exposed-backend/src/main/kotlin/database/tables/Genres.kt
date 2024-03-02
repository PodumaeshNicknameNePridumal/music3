package database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime


object Genres: IntIdTable() {
    val name = text(name = "name")
    val deleted = datetime("deleted").nullable()
}
