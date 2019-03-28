package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.Command
import pember.example.ktrestaurant.core.identifiers.RestaurantId

class OpenRestaurant(val name: String, override val userId: String): Command

class HireEmployee(val restaurantId: RestaurantId, val employeeName: String, override val userId: String): Command

class ChangeName(val restaurantId: RestaurantId, val updatedName: String, override val userId: String): Command