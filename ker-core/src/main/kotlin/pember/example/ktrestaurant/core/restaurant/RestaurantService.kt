package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.EntityWithEvents
import pember.example.ktrestaurant.core.events.EventService
import pember.example.ktrestaurant.core.identifiers.StreamId
import java.time.LocalDateTime
import java.util.*

class RestaurantService(val eventService: EventService, val restaurantRepository: RestaurantRepository){
    fun openRestaurant(command: OpenRestaurant): Restaurant {
        val restaurant = Restaurant(id = StreamId(UUID.randomUUID()))
        val now = LocalDateTime.now()

        return eventService.applyAndPersist(EntityWithEvents(restaurant, listOf(
                RestaurantCreated(command.name, restaurant.id, 1, command.userId, now)
        )))
    }

    fun hireEmployee(command: HireEmployee): Restaurant {
        return hireEmployee(this.restaurantRepository.load(command.restaurantId), command)
    }

    /**
     * Execute a HireEmployee command with the restaurant already loaded
     */
    fun hireEmployee(restaurant: Restaurant, command: HireEmployee): Restaurant {
        return eventService.applyAndPersist(EntityWithEvents(restaurant, listOf(
                EmployeeHired(command.employeeName, restaurant.id, restaurant.revision+1, command.userId, LocalDateTime.now())
        )))
    }

    fun changeName(restaurant: Restaurant, command: ChangeName): Restaurant {
        return eventService.applyAndPersist(EntityWithEvents(restaurant, listOf(
                NameChanged(command.updatedName, restaurant.id, restaurant.revision+1, command.userId, LocalDateTime.now())
        )))
    }
}