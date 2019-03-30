package pember.example.ktrestaurant.app.controllers

import io.micronaut.http.annotation.*
import pember.example.ktrestaurant.app.mappings.CreateRestaurantMetaCommand
import pember.example.ktrestaurant.app.mappings.RestaurantResponse
import pember.example.ktrestaurant.core.restaurant.OpenRestaurant
import pember.example.ktrestaurant.core.restaurant.Restaurant
import pember.example.ktrestaurant.core.restaurant.RestaurantService
import javax.validation.Valid

@Controller("/restaurant")
class RestaurantController(private val restaurantService: RestaurantService) {
    // would normally use auth to grab user details for internal commands, but just default for now
    private val DEFAULT_USER = "defaultUser"

    @Get("/")
    fun index(): List<Restaurant> {
        return restaurantService.listCurrent()
    }

    @Post("/")
    fun create(@Body @Valid command:CreateRestaurantMetaCommand): RestaurantResponse {
        // use a dummy
        val restaurant = restaurantService.openRestaurant(OpenRestaurant(command.name, DEFAULT_USER))

        return RestaurantResponse(restaurant)
    }
}