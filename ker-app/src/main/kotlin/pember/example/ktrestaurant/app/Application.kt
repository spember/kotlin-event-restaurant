package pember.example.ktrestaurant.app

import io.micronaut.runtime.Micronaut
import pember.example.ktrestaurant.core.identifiers.StreamId
import pember.example.ktrestaurant.core.restaurant.Restaurant
import java.util.*

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val p = Restaurant("Alice's", 100, id = StreamId(UUID.randomUUID()))
        println(p)
        println(p.label)
        Micronaut.build()
                .packages("pember.example.ktrestaurant.app")
                .mainClass(Application.javaClass)
                .start()
    }
}