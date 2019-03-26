package pember.example.ktrestaurant.core.events

import pember.example.ktrestaurant.core.identifiers.StreamId

interface EventRepository {

    fun persistEvents(events: List<Event>)

    fun loadAllForEntity(id: StreamId): List<Event>
}