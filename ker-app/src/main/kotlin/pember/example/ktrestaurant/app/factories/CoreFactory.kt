package pember.example.ktrestaurant.app.factories

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import pember.example.ktrestaurant.core.events.EventRepository
import pember.example.ktrestaurant.core.events.EventService
import pember.example.ktrestaurant.core.restaurant.RestaurantRepository
import pember.example.ktrestaurant.core.restaurant.RestaurantService
import pember.example.ktrestaurant.storage.inmemory.InMemoryEventRepository
import javax.inject.Singleton

@Factory
class CoreFactory {

    @Bean
    @Singleton
    fun eventService(eventRepository: EventRepository): EventService {
        return EventService(eventRepository)
    }

    @Bean
    @Singleton
    fun restaurantService(eventService: EventService, restaurantRepository: RestaurantRepository): RestaurantService {
        return RestaurantService(eventService, restaurantRepository)
    }
}