package mappers

import database.entities.Track
import models.TrackModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

interface ITrackMapper {
    fun trackToTrackModel(track: Track): TrackModel
}

@Module
@ComponentScan
class MappersModule

@Single
class TrackMapper : ITrackMapper {
    override fun trackToTrackModel(track: Track): TrackModel {
        return TrackModel(
            id = track.id.value,
            name = track.name,
            duration = track.duration,
            deleted = track.deleted
        )
    }
}
