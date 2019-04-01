package pember.example.ktrestaurant.core.menu

import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.identifiers.MenuId
import java.time.LocalDateTime

data class ItemAddedToMenu(val updatedName: String, override val root: MenuId, override val revision: Int, override val userId: String, override val timestamp: LocalDateTime): Event