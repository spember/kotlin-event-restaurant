package pember.example.ktrestaurant.app.controllers

import io.micronaut.http.annotation.*
import pember.example.ktrestaurant.core.restaurant.OpenRestaurant
import pember.example.ktrestaurant.core.restaurant.Restaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantService
import javax.validation.Valid

@Controller("/restaurant")
class RestaurantController(private val restaurantService: RestaurantService) {

    @Get("/")
    fun index(): List<Restaurant> {
        return restaurantService.listCurrent()
    }

    @Post("/")
    fun create(@Body @Valid command:OpenRestaurant): Restaurant {
        println("Opening restaurant ${command.name}")
        val restaurant = restaurantService.openRestaurant(command)
        println("Created ${restaurant.name}, ${restaurant.id}")
        return restaurant
    }
}