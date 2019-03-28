package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.identifiers.RestaurantId
import pember.example.ktrestaurant.core.identifiers.StreamId

interface RestaurantRepository {

    /**
     * Loads the current State of a restaurant given its id.
     */
    fun load(restaurantId: RestaurantId): Restaurant

}