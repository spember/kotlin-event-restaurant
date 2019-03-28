package pember.example.ktrestaurant.core.restaurant

import com.fasterxml.jackson.annotation.JsonProperty
import pember.example.ktrestaurant.core.Command
import pember.example.ktrestaurant.core.identifiers.RestaurantId

class OpenRestaurant(@JsonProperty("name") val name: String, @JsonProperty("userId") override val userId: String): Command

class HireEmployee(val restaurantId: RestaurantId, val employeeName: String, override val userId: String): Command

class ChangeName(val restaurantId: RestaurantId, val updatedName: String, override val userId: String): Command