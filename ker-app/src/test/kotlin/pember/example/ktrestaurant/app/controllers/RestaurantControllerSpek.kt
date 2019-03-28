package pember.example.ktrestaurant.app.controllers

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import pember.example.ktrestaurant.core.restaurant.OpenRestaurant
import pember.example.ktrestaurant.core.restaurant.Restaurant
import kotlin.test.assertEquals

object RestaurantControllerSpek : Spek({

    describe("RestaurantController Suite") {
        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client : HttpClient = HttpClient.create(embeddedServer.url)

        it("test /restaurant responds empty") {
            val rsp : String = client.toBlocking().retrieve("/restaurant")
            assertEquals(rsp, "[]")
        }

        it (" test posting to /restaurant with valid body") {
            val rsp : HttpResponse<Any> = client.toBlocking().exchange(HttpRequest.POST("/restaurant", OpenRestaurant("Bob's Burgers", "test123")))

            println("Received ${rsp}, restaurant = ${rsp.body.get()}")
            assertEquals(rsp.body(), null)
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})