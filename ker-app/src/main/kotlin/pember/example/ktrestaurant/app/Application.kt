package pember.example.ktrestaurant.app

import io.micronaut.runtime.Micronaut
import pember.example.ktrestaurant.core.identifiers.RestaurantId
import pember.example.ktrestaurant.core.restaurant.Restaurant

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val p = Restaurant("Alice's", 100, RestaurantId())
        println(p)
        println(p.label)
        Micronaut.build()
                .packages("pember.example.ktrestaurant.app")
                .mainClass(Application.javaClass)
                .start()
    }
}