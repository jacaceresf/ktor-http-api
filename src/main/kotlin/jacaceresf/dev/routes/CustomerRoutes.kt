package jacaceresf.dev.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import jacaceresf.dev.models.Customer
import jacaceresf.dev.models.customerStorage


fun Route.customerRouting() {

    /// some block HTTP endpoints for the customer API.

    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(customerStorage)
            } else {
                call.respondText("No customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}") {

            ///this will be null if no parameter {id} was passed in
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )

            val customer = customerStorage.find { it.id == id } ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )

            call.respond(customer)
        }
        post {
            ///the receive function is integrated with the defined content negotiation
            /// and it automatically deserializes the JSON inside the request body into a Customer
            val customerReq = call.receive<Customer>()

            customerStorage.add(customerReq)

            call.respondText("Customer stored correctly!", status = HttpStatusCode.Created)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)

            if (customerStorage.removeIf { it.id == id }) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}


fun Application.registerCustomerRoutes() {
    routing {
        customerRouting()
    }
}