package pember.example.ktrestaurant.storage.inmemory

import pember.example.ktrestaurant.core.events.EventRepository
import pember.example.ktrestaurant.core.identifiers.StreamId
import pember.example.ktrestaurant.core.restaurant.Restaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantRepository

class InMemoryRestaurantRepository(val eventRepository: EventRepository): RestaurantRepository{

    override fun load(restaurantId: StreamId): Restaurant {
        val restaurant = Restaurant(restaurantId)
        eventRepository.loadAllForEntity(restaurantId).forEach { event ->
            restaurant.apply(event)
        }
        return restaurant
    }
}