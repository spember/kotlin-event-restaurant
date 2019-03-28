package pember.example.ktrestaurant.storage.inmemory

import pember.example.ktrestaurant.core.events.EventRepository
import pember.example.ktrestaurant.core.identifiers.RestaurantId
import pember.example.ktrestaurant.core.restaurant.Restaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantRepository

class InMemoryRestaurantRepository(val eventRepository: EventRepository): RestaurantRepository{

    private val cache: MutableMap<RestaurantId, Restaurant> = mutableMapOf()

    override fun load(restaurantId: RestaurantId): Restaurant {
        if (cache.containsKey(restaurantId)) {
            return cache[restaurantId]!!
        }
        val restaurant = Restaurant(restaurantId)
        eventRepository.loadAllForEntity(restaurantId).forEach { event ->
            restaurant.apply(event)
        }
        return restaurant
    }

    override fun loadAll(): List<Restaurant> {
        return cache.values.toList()
    }

    override fun loadIds(): Set<RestaurantId> {
        return cache.keys
    }

    override fun update(restaurant: Restaurant): Restaurant {
        cache[restaurant.id] = restaurant
        println("cached restaurant $restaurant and returning")
        return restaurant
    }
}