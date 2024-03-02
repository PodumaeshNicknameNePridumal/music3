package managers

import models.TrackModel
import models.UpdTrackModel

interface ITrackManager {
    suspend fun getAllTracks(): List<TrackModel>
    suspend fun getTrackById(id: Int): TrackModel?
    suspend fun createTrack(trackModel: UpdTrackModel): Int
    suspend fun updateTrack(id: Int, updatedTrackModel: UpdTrackModel): TrackModel?
    suspend fun deleteTrack(id: Int): Boolean
}