package pember.example.ktrestaurant.app.controllers

import io.micronaut.context.ApplicationContext
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.test.assertEquals

object HelloControllerSpec: Spek({

    describe("HelloController Suite") {
        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client : HttpClient = HttpClient.create(embeddedServer.url)

        it("test /hello responds Hello World") {
            val rsp : String = client.toBlocking().retrieve("/hello")
            assertEquals(rsp, "Hello World")
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})