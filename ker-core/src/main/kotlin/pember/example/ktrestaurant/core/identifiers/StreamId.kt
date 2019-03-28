package pember.example.ktrestaurant.core.identifiers

import java.util.*

abstract class StreamId(val value: UUID) {
    constructor(): this(UUID.randomUUID())
}

class RestaurantId : StreamId()