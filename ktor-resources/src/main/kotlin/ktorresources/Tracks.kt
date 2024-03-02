package ktorresources

import io.ktor.resources.*
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable


@Resource("/tracks")
class Tracks(val sort: String? = "new") {
    @Resource("new")
    class New(val parent: Tracks = Tracks())

    @Resource("{id}")
    class Id(val parent: Tracks = Tracks(), val id: Int) {
        @Resource("edit")
        class Edit(val parent: Id)
        @Resource("del")
        class Del(val parent: Id)
    }
}