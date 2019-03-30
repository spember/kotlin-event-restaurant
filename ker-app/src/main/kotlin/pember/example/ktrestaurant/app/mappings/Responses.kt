package pember.example.ktrestaurant.app.mappings

import com.fasterxml.jackson.annotation.JsonProperty
import pember.example.ktrestaurant.core.restaurant.Restaurant

class RestaurantResponse(@JsonProperty("name") val name:String,
                         @JsonProperty("yearOpened")val yearOpened: Int,
                         @JsonProperty("id")val id: String) {

    constructor(restaurant: Restaurant): this(restaurant.name, restaurant.yearOpened, restaurant.id.value.toString())
}