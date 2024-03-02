package repositories

import database.entities.Track
import mappers.ITrackMapper
import models.TrackModel
import models.UpdTrackModel
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class RepositoryModule

@Single
class TrackRepository(private val trackMapper: ITrackMapper) : ITrackRepository {
    override suspend fun getAllTracks(): List<TrackModel> = transaction {
        Track.all().map { trackMapper.trackToTrackModel(it) }
    }

    override suspend fun getTrackById(id: Int): TrackModel? = transaction {
        Track.findById(id)?.let { trackMapper.trackToTrackModel(it) }
    }

    override suspend fun createTrack(trackModel: UpdTrackModel): Int {
         return transaction {
             Track.new {
                name = trackModel.name
                duration = trackModel.duration
                deleted = trackModel.deleted
            }.id.value
        }
    }

    override suspend fun updateTrack(id: Int, updTrackModel: UpdTrackModel): TrackModel? {
        return transaction {
            Track.findById(id)?.apply {
                name = updTrackModel.name
                duration = updTrackModel.duration
                deleted = updTrackModel.deleted
            }?.let { trackMapper.trackToTrackModel(it) }
        }
    }
    override suspend fun deleteTrack(id: Int): Boolean = transaction {   Track.findById(id)?.let { it.delete(); true } ?: false}
}


