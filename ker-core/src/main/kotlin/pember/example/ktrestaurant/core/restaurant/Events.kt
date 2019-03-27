package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.identifiers.StreamId
import java.time.LocalDateTime

data class RestaurantCreated(val name: String, override val root: StreamId, override val revision: Int, override val userId: String, override val timestamp: LocalDateTime): Event
data class EmployeeHired(val employeeName: String, override val root: StreamId, override val revision: Int, override val userId: String, override val timestamp: LocalDateTime): Event
data class EmployeeFired(val employeeName: String, override val root: StreamId, override val revision: Int, override val userId: String, override val timestamp: LocalDateTime): Event
data class NameChanged(val updatedName: String, override val root: StreamId, override val revision: Int, override val userId: String, override val timestamp: LocalDateTime): Event