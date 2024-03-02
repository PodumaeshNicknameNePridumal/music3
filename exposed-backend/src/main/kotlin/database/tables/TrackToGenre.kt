package database.tables

import org.jetbrains.exposed.sql.Table


object TrackToGenre: Table() {
    val trackID = reference(name = "product", foreign = Tracks)
    val genreID = reference(name = "category", foreign = Genres)
}