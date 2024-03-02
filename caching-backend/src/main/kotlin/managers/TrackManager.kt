import io.github.reactivecircus.cache4k.Cache
import managers.ITrackManager
import models.TrackModel
import models.UpdTrackModel
import repositories.ITrackRepository


abstract class Manager<ID : Any, Model: Any>(
    open val cache: Cache<ID, Model>
)

open class TrackManager(
    cache: Cache<Int, TrackModel>,
    open val repository: ITrackRepository
) : Manager<Int, TrackModel>(cache), ITrackManager{
    override suspend fun getAllTracks(): List<TrackModel> {
        return repository.getAllTracks()
    }

    override suspend fun getTrackById(id: Int): TrackModel? {
        return cache.get(key = id) {
            repository.getTrackById(id)!!
        }
    }

    override suspend fun createTrack(trackModel: UpdTrackModel): Int {
        val insertedTrackId = repository.createTrack(trackModel)
        cache.put(key = insertedTrackId, value = TrackModel(insertedTrackId,trackModel))
        return insertedTrackId
    }

    override suspend fun updateTrack(id: Int, updatedTrackModel: UpdTrackModel): TrackModel? {
        val updated = repository.updateTrack(id, updatedTrackModel)
        cache.put(key = id, value = TrackModel(id,updatedTrackModel))
        return updated
    }

    override suspend fun deleteTrack(id: Int): Boolean {
        val isDeleted = repository.deleteTrack(id)
        if (isDeleted) {
            cache.invalidate(key = id)
        }
        return isDeleted
    }
}

