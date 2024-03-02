package database.entities

import database.tables.Genres
import database.tables.Tracks
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Genre(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Track>(Tracks)

    var name by Genres.name
    var deleted by Genres.deleted
    fun markAsDeleted(): Boolean {
        return if (!isDeleted()) {
            deleted = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            true
        } else false
    }

    fun isDeleted(): Boolean = deleted != null
}