package database.entities

import database.tables.Tracks
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class Track(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Track>(Tracks)

    var name by Tracks.name
    var duration by Tracks.duration
    var deleted by Tracks.deleted
    constructor(id: EntityID<Int>, name: String, duration: Int, deleted: LocalDateTime? = null) : this(id) {
        this.name = name
        this.duration = duration
        this.deleted = deleted
    }
//    constructor(name: String, duration: Int, deleted: LocalDateTime? = null) : this(EntityID(0, Tracks)) {
//        this.name = name
//        this.duration = duration
//        this.deleted = deleted
//    }

    fun markAsDeleted(): Boolean {
        return if (!isDeleted()) {
            deleted = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            true
        } else false
    }

    fun isDeleted(): Boolean = deleted != null
}