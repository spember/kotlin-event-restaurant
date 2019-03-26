package pember.example.ktrestaurant.core.events

import pember.example.ktrestaurant.core.identifiers.StreamId
import java.time.LocalDateTime

interface Event {
    // the event stream root / entity this event belongs to
    val root: StreamId
    val revision: Int
    // the user or application that created this event
    val userId: String
    val timestamp: LocalDateTime

}