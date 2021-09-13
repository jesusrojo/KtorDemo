package com.jesusrojo.ktorserver

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.concurrent.TimeUnit


//https://ktor.io/docs/gradle.html#create-embedded-server

//1 Create a server using embeddedServer
fun main() {

    println("HelloWorldApplication #")
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world! embeddedServer... #")
            }
        }
    }.start(wait = true)

//////STOP THE SERVER
//    // https://dev.to/viniciusccarvalho/graceful-shutdown-of-ktor-applications-1h53
//    // https://stackoverflow.com/questions/67679674/start-and-stop-server-when-i-want
//    Runtime.getRuntime().addShutdownHook(Thread {
//        server.stop(1, 5, TimeUnit.SECONDS)
//    })
//    Thread.currentThread().join()
//////
}


// 2 Create a server using EngineMain
//fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)
//
//fun Application.module(testing: Boolean = false) {
//    routing {
//        get("/") {
//            call.respondText("Hello, world! EngineMain")
//        }
//    }
//}



//LIFE CYCLE https://dev.to/viniciusccarvalho/graceful-shutdown-of-ktor-applications-1h53
fun Application.module(testing: Boolean = false) {
    environment.monitor.subscribe(ApplicationStarted) {
        println("My app is ready to roll #")
    }
    environment.monitor.subscribe(ApplicationStopped) {
        println("Time to clean up #")
    }
}