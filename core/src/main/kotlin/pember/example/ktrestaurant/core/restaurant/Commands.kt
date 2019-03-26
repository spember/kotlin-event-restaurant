package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.Command
import pember.example.ktrestaurant.core.identifiers.StreamId

class OpenRestaurant(val name: String, override val userId: String): Command

class HireEmployee(val restaurantId: StreamId, val employeeName: String, override val userId: String): Command

class ChangeName(val restaurantId: StreamId, val updatedName: String, override val userId: String): Command