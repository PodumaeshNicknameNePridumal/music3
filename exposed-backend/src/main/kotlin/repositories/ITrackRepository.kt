package repositories

import models.TrackModel
import models.UpdTrackModel


interface ITrackRepository {
    suspend fun getAllTracks(): List<TrackModel>
    suspend fun getTrackById(id: Int): TrackModel?
    suspend fun createTrack(trackModel: UpdTrackModel): Int
    suspend fun updateTrack(id: Int, updTrackModel: UpdTrackModel): TrackModel?
    suspend fun deleteTrack(id: Int): Boolean
}
