package models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Serializable
data class TrackModel(
    val id: Int,
    val name: String,
    val duration: Int,
    val deleted: LocalDateTime? = null
) {
    constructor(id: Int, updTrackModel: UpdTrackModel) : this(
        id = id,
        name = updTrackModel.name,
        duration = updTrackModel.duration,
        deleted = updTrackModel.deleted
    )
}

@Serializable
data class UpdTrackModel(
    val name: String,
    val duration: Int,
    val deleted: LocalDateTime? = null
)