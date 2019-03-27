package pember.example.ktrestaurant.core

import pember.example.ktrestaurant.core.events.Event

class EntityWithEvents<E: EventSourcedEntity> (val entity: E, val events: List<Event>)
