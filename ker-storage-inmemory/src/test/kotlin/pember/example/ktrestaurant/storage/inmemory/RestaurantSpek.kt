package pember.example.ktrestaurant.storage.inmemory

import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import pember.example.ktrestaurant.core.events.EventService
import pember.example.ktrestaurant.core.restaurant.ChangeName
import pember.example.ktrestaurant.core.restaurant.HireEmployee
import pember.example.ktrestaurant.core.restaurant.OpenRestaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantService
import java.time.LocalDateTime
import kotlin.test.assertTrue

object RestaurantSpek: Spek({
    describe("Restaurant Service and Repository") {
        val eventRepository = InMemoryEventRepository()
        val restaurantRepository = InMemoryRestaurantRepository(eventRepository)
        val eventService = EventService(eventRepository)
        val uId = "User123"
        val restaurantService = RestaurantService(eventService, restaurantRepository)

        it ("Should work") {
            val restaurant = restaurantService.openRestaurant(OpenRestaurant("Alice's", uId))
            restaurantService.hireEmployee(restaurant, HireEmployee(restaurant.id, "Bob the Chef", uId))
            assertEquals(restaurant.name, "Alice's")
            assertEquals(restaurant.employeeCount, 1)
        }

        it ("Should read the restaurant from cache") {
            val restaurant = restaurantService.openRestaurant(OpenRestaurant("Bob's Burgers", uId))
            restaurantService.hireEmployee(HireEmployee(restaurant.id, "Bob the Chef", uId))
            restaurantService.hireEmployee(HireEmployee(restaurant.id, "Lisa", uId))

            // reload
            val testRestaurant = restaurantRepository.load(restaurant.id)
            assertEquals(2, testRestaurant.employeeCount)
            assertEquals("Bob's Burgers", testRestaurant.name)
            assertEquals(LocalDateTime.now().year, testRestaurant.yearOpened)
            assertTrue { testRestaurant.currentEmployees.contains("Lisa") }
        }

        it ("Should handle multiple restaurants") {
            var restaurant1 = restaurantService.openRestaurant(OpenRestaurant("Alice's Restaurannnnn", uId))
            restaurant1 = restaurantService.hireEmployee(HireEmployee(restaurant1.id, "Alice", uId))

            var restaurant2 = restaurantService.openRestaurant(OpenRestaurant("Bob's Burgers", uId))
            restaurant2 = restaurantService.hireEmployee(HireEmployee(restaurant2.id, "Bob the Chef", uId))


            restaurant1 = restaurantService.changeName(restaurant1, ChangeName(restaurant1.id, "AR", uId))
            restaurant2 = restaurantService.hireEmployee(HireEmployee(restaurant2.id, "Lisa", uId))
            restaurant1 = restaurantService.changeName(restaurant1, ChangeName(restaurant1.id, "Alice's Restaurant", uId))

            val testR1 = restaurantRepository.load(restaurant1.id)
            val testR2 = restaurantRepository.load(restaurant2.id)
            assertEquals("Alice's Restaurant", testR1.name)
            assertEquals(1, testR1.employeeCount)

            assertEquals("Bob's Burgers", testR2.name)
            assertEquals(2, testR2.employeeCount)


        }
    }
})