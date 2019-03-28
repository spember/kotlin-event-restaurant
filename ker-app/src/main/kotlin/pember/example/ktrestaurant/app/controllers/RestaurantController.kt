package pember.example.ktrestaurant.app.controllers

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import pember.example.ktrestaurant.core.restaurant.Restaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantService

@Controller("/restaurant")
class RestaurantController(val restaurantService: RestaurantService) {


    @Get("/")
    fun index(): List<Restaurant> {
        return restaurantService.listCurrent()
    }
}