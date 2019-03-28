package pember.example.ktrestaurant.app.factories

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import pember.example.ktrestaurant.core.events.EventRepository
import pember.example.ktrestaurant.core.restaurant.RestaurantRepository
import pember.example.ktrestaurant.storage.inmemory.InMemoryEventRepository
import pember.example.ktrestaurant.storage.inmemory.InMemoryRestaurantRepository
import javax.inject.Singleton

@Factory
class RepositoryFactory {

    @Bean
    @Singleton
    fun eventRepository(): EventRepository {
        return InMemoryEventRepository()
    }

    @Bean
    @Singleton
    fun restaurantRepository(eventRepository: EventRepository): RestaurantRepository {
        return InMemoryRestaurantRepository(eventRepository)
    }
}