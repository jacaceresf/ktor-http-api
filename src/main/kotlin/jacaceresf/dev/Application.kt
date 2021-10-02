package jacaceresf.dev

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import jacaceresf.dev.plugins.*
import jacaceresf.dev.routes.registerCustomerRoutes

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {

    ///Content negotiation allows to the server to examine the ACCEPT header
    // see if it can serve the specific type and if so, return the result
    install(ContentNegotiation) {
        json()
    }

    ///we need to register the routes so Ktor can know about them.
    registerCustomerRoutes()
}

