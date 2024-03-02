package managers

import TrackManager
import io.github.reactivecircus.cache4k.Cache
import mappers.TrackMapper
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import repositories.TrackRepository
import kotlin.time.Duration.Companion.hours


@Module()
@ComponentScan
class ManagerModule {
    @Single
    fun trackManager(
        mapper: TrackMapper
    ): ITrackManager =
        TrackManager(
            cache = Cache.Builder()
                .expireAfterAccess(24.hours)
                .build(),
            repository = TrackRepository(
                trackMapper = mapper
            )
        )
}
