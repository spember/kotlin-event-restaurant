package pember.example.ktrestaurant.core.menu

import pember.example.ktrestaurant.core.EventSourcedEntity
import pember.example.ktrestaurant.core.events.Event
import pember.example.ktrestaurant.core.identifiers.MenuId
import pember.example.ktrestaurant.core.identifiers.RestaurantId

class Menu (val restaurantId: RestaurantId, id: MenuId): EventSourcedEntity<MenuId>(id) {

    private var items: List<MenuItemPricing> = mutableListOf()

    override fun <T : Event> handle(event: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

/**
 * Maintains the context of a MenuItem within a Menu, e.g. pricing
 */
class MenuItemPricing (val menuItem: MenuItem, var priceInCents: Int)