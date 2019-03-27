package pember.example.ktrestaurant.core

import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.identifiers.StreamId
import java.lang.RuntimeException

abstract class EventSourcedEntity(val id:StreamId) {
    var revision: Int = 0

    abstract fun <T: Event> handle(event: T)

    fun <T: Event> apply(event: T): EventSourcedEntity {
        if (event.revision != revision+1) {
            throw RuntimeException("Expected event of revision ${revision+1} but received ${event.revision}")
        }
        handle(event)
        this.revision = event.revision
        return this
    }
}