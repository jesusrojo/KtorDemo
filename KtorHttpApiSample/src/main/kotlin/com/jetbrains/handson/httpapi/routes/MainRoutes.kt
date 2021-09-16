package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.models.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.mainRouting() {
    route("/") {
        get {
            call.respondText("Welcome!!!\nUse:"
                    + "\nORDER"
                    + "\nhttp://localhost:8080/order"
                    + "\nhttp://localhost:8080/order/1"
                    + "\nhttp://localhost:8080/order/1/total"
                    + "\nCUSTOMER"
                    + "\nhttp://localhost:8080/customer"
                    + "\nhttp://localhost:8080/customer/1"
                    + "\nhttp://localhost:8080/customer/POST-COSTUMER"
                    + "\nhttp://localhost:8080/customer/DELETE/1"
                , status = HttpStatusCode.NotFound)
        }
    }
}


fun Application.registerMainRoutes() {
    routing {
        mainRouting()
    }
}


