package pember.example.ktrestaurant.app.controllers

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import pember.example.ktrestaurant.app.mappings.CreateRestaurantMetaCommand
import pember.example.ktrestaurant.app.mappings.RestaurantResponse
import pember.example.ktrestaurant.core.restaurant.OpenRestaurant
import pember.example.ktrestaurant.core.restaurant.Restaurant
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

object RestaurantControllerSpek : Spek({

    describe("RestaurantController Suite") {
        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client : HttpClient = HttpClient.create(embeddedServer.url)

        it("test /restaurant responds empty") {
            val rsp : String = client.toBlocking().retrieve("/restaurant")
            assertEquals(rsp, "[]")
        }

        it (" test posting to /restaurant with valid body") {
            val rsp : HttpResponse<RestaurantResponse> = client.toBlocking().exchange(
                    HttpRequest.POST("/restaurant", CreateRestaurantMetaCommand("Bob's Burgers")),
                    Argument.of(RestaurantResponse::class.java)

            )
            assertEquals(rsp.status()!!, HttpStatus.OK)
            println("Received ${rsp}, restaurant = ${rsp.body()}")

            assertNotNull(rsp.body())
            val restaurant: RestaurantResponse = rsp.body()!!
            assertEquals("Bob's Burgers", restaurant.name)
            assertEquals(LocalDateTime.now().year, restaurant.yearOpened)
            assertNotNull(restaurant.id)
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})