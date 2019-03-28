package pember.example.ktrestaurant.core.restaurant

import pember.example.ktrestaurant.core.identifiers.RestaurantId

/**
 * Responsible for maintaining current state of restaurants. For certain entities, caching in memory would be
 * sufficient, while others might require persisting to disc. Like all things, a tradeoff.
 */
interface RestaurantRepository {

    /**
     * Loads the current State of a restaurant given its [restaurantId].
     */
    fun load(restaurantId: RestaurantId): Restaurant

    fun loadAll(): List<Restaurant>


    /**
     * Return the RestaurantIds of all known Restaurants. Note that if you're looking for a specific time, Restaurants
     * may have closed or not exist yet, so utilize the events to determine if the id is event valid
     */
    fun loadIds(): Set<RestaurantId>


    /**
     * Update the current state of [restaurant]
     */
    fun update(restaurant: Restaurant) : Restaurant

}