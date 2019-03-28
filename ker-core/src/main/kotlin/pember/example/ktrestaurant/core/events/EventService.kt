package pember.example.ktrestaurant.core.events

import pember.example.ktrestaurant.core.EntityWithEvents
import pember.example.ktrestaurant.core.EventSourcedEntity
import pember.example.ktrestaurant.core.identifiers.StreamId

class EventService (val eventRepository: EventRepository) {

    fun <I:StreamId, E : EventSourcedEntity<I>> applyAndPersist(entityWithEvents: EntityWithEvents<I, E>): E  {
        entityWithEvents.events.forEach { event -> entityWithEvents.entity.apply(event) }
        eventRepository.persistEvents(entityWithEvents.events)
        return entityWithEvents.entity
    }

    fun <I: StreamId, E: EventSourcedEntity<I>> applyLatestEvents(entity: E): E {
        eventRepository.loadAllAfterRevision(entity.id, entity.revision).forEach { event -> entity.apply(event) }
        return entity
    }
}