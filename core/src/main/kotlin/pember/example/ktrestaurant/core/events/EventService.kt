package pember.example.ktrestaurant.core.events

import pember.example.ktrestaurant.core.EntityWithEvents
import pember.example.ktrestaurant.core.EventSourcedEntity

class EventService (val eventRepository: EventRepository) {

    fun <E : EventSourcedEntity> applyAndPersist(entityWithEvents: EntityWithEvents<E>): E  {
        entityWithEvents.events.forEach { event -> entityWithEvents.entity.apply(event) }
        eventRepository.persistEvents(entityWithEvents.events)
        return entityWithEvents.entity
    }
}