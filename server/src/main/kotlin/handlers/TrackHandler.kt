package main.handlers


import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktorresources.Tracks
import managers.ITrackManager
import models.UpdTrackModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single


@Module
@ComponentScan
class HandlerModule
@Single
class TrackHandler(private val trackManager: ITrackManager) {
    fun Route.configureTracksRoutes(): Route {
        return route("/") {
            get<Tracks> {
                try {
                    val tracks = trackManager.getAllTracks()
                    call.respond(tracks)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to get tracks")
                }
            }

            get<Tracks.Id> {track ->
                try {
                    val track = trackManager.getTrackById(track.id)
                    if (track != null) {
                        call.respond(track)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Track not found")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to get track")
                }
            }

            post<Tracks.New> {
                try {
                    val track = call.receive<UpdTrackModel>()
                    val insertedTrackId = trackManager.createTrack(track)
                    call.respond(HttpStatusCode.Created, mapOf("id" to insertedTrackId))
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to create track")
                }
            }

            put<Tracks.Id.Edit> {
                try {
                    val updatedTrack = call.receive<UpdTrackModel>()
                    val updated = trackManager.updateTrack(it.parent.id, updatedTrack)
                    if (updated != null) {
                        call.respond(updated)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Track not found")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to update track")
                }
            }

            delete<Tracks.Id.Del> { track->
                try {
                    val isDeleted = trackManager.deleteTrack(track.parent.id)
                    if (isDeleted) {
                        call.respond(HttpStatusCode.NoContent)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Track not found")
                    }
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.InternalServerError, "Failed to delete track")
                }
            }
        }

    }
}
