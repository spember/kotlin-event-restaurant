package pember.example.ktrestaurant.core.restaurant


import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.EventSourcedEntity
import pember.example.ktrestaurant.core.identifiers.RestaurantId

class Restaurant(var name: String, var employeeCount: Int, id: RestaurantId) : EventSourcedEntity<RestaurantId>(id) {
    var currentEmployees : List<String> = listOf()
    var yearOpened: Int = 0


    override fun <T : Event> handle(event: T) {
        // there should be a more streamlined way to do this
        when(event) {
            is RestaurantCreated -> handle(event)
            is EmployeeHired -> handle(event)
            is NameChanged -> handle(event)
            else -> TODO("Missing cast for event (${event.javaClass})")
        }
    }

    var label: String = "Restaurant: $name"

    constructor(id: RestaurantId): this("", 0, id)


    fun handle(event: RestaurantCreated) {
        this.name = event.name
        this.yearOpened = event.timestamp.year
    }

    fun handle(event: EmployeeHired) {
        currentEmployees = listOf(currentEmployees, listOf(event.employeeName)).flatten()
        this.employeeCount += 1
    }

    fun handle(event: NameChanged) {
        this.name = event.updatedName
    }
}