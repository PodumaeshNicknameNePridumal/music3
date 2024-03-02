package database.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.datetime


object Tracks : IntIdTable() {
    val name = text("name")
    val duration = integer("duration")
    val deleted = datetime("deleted").nullable()
}
