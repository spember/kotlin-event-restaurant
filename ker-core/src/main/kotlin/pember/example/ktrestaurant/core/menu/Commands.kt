package pember.example.ktrestaurant.core.menu

import pember.example.ktrestaurant.core.Command

class AddItemToMenu(val menuItem: MenuItem, val initialPrice: Int, override val userId: String): Command

class AdjustPrice(val menuItem: MenuItem, val updatedPriceInCents: Int, override val userId: String): Command

class RemoveItemFromMenu(val menuItem: MenuItem, override val userId: String): Command

