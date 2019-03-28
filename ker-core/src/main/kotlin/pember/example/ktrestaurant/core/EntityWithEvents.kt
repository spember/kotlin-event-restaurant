package pember.example.ktrestaurant.core

import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.identifiers.StreamId

class EntityWithEvents<I: StreamId, E: EventSourcedEntity<I>> (val entity: E, val events: List<Event>)
