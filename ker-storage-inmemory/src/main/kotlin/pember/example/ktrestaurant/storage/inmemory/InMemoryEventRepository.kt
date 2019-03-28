package pember.example.ktrestaurant.storage.inmemory

import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.events.EventRepository
import pember.example.ktrestaurant.core.identifiers.StreamId

class InMemoryEventRepository: EventRepository {
    private val journal: MutableList<Event> = mutableListOf()

    override fun persistEvents(events: List<Event>) {
        journal.addAll(events)
    }

    override fun loadAllForEntity(id: StreamId): List<Event> {
        return journal.filter { it.root == id }
    }

    override fun loadAllAfterRevision(id: StreamId, revision: Int): List<Event> {
        return journal.filter {it.root == id && it.revision > revision}
    }
}