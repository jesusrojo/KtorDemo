package com.jesusrojo.ktorappwithserver

import com.jesusrojo.ktorappwithserver.model.FakeUtil
import com.jesusrojo.ktorappwithserver.model.Request
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.*
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.*
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.*
import io.ktor.server.netty.Netty
import java.text.DateFormat
import java.util.concurrent.TimeUnit

class KtorServer {

    companion object {
        const val localhos8080 = "localhost:8080"
        const val localhos8080TestJson = "$localhos8080/test-json"
        const val localhos8080TestResponse = "$localhos8080/test-response"
        const val localhos8080TestString = "$localhos8080/test-string/abracadabra"
        const val localhos8080TestRawData = "$localhos8080/rawData"
        const val localhos8080Test2 = "$localhos8080/test2"
    }
    private lateinit var server: ApplicationEngine

    fun startServer() {
        println("startServer #")
        server = embeddedServer(Netty, 8080) {

            install(StatusPages) {
                exception<Throwable> { e ->
                    call.respondText(
                        e.localizedMessage,
                        ContentType.Text.Plain,
                        HttpStatusCode.InternalServerError
                    )
                }
            }
            install(ContentNegotiation) {
                gson {
                    setDateFormat(DateFormat.LONG)
                    setPrettyPrinting()
                    disableHtmlEscaping()
                }
            }

            routing {

                // http://localhost:8080
                get("/") {
                    val random = (0..100).random()
                    call.respondText("RANDOM: $random", ContentType.Text.Html)
                }
                /* RANDOM: 76*/

                // http://localhost:8080/test-json
                get("/test-json") {
                    call.respondText(
                        FakeUtil.Companion.getFakeJsonRawData(),
                        ContentType.Application.Json
                    )
                }
                /*{
                            "id": 0,
                            "title": "title0",
                            "description": "description0"
                        }*/

                // http://localhost:8080/test-response
                get("/test-response") {
                    val response = FakeUtil.Companion.getFakeJsonListRawDatas()
                    call.respondText(response)
                }
                /*{
                            "idRawData": 1,
                            "title": "title1",
                            "description": "description1"
                        }
                        {
                            "idRawData": 2,
                            "title": "title2",
                            "description": "description2"
                        }*/

                // http://localhost:8080/test-string/abracadabra
                get("/test-string/{string}") {
                    val string = call.parameters["string"] ?: ""
                    call.respond(string)
                }
                /*abracadabra*/
                // http://localhost:8080/aaa
//            get("/{string}") {
//                val string = call.parameters["string"] ?: ""
//                call.respond(string)
//            }

                // http://localhost:8080/rawdata
                get("/rawdata") {
                    call.respond(FakeUtil.Companion.getFakeResponse())
                }
                /*{
                      "status": "OK",
                      "items": [
                        {
                          "idRawData": 10,
                          "title": "title10",
                          "description": "description10"
                        },
                        {
                          "idRawData": 11,
                          "title": "title11",
                          "description": "description11"
                        }
                      ],
                      "hasMore": false
                }*/

                // https://medium.com/swlh/ktor-post-request-with-parameters-ed4b3e9674ca
                // IN BASH IN CMD WINDOWS NOT WORKING
                // curl -X POST -F param1=value1 -F param2=value2 http://localhost:8080/test2
                // This is a test POST request with parameter values value1 and value2 OK IN BASH
                // http://localhost:8080/test2?param1=aaa&param2=bbb TODO ? CAN NOT PASS PARAMETERS LIKE SO
                // http://localhost:8080/test2/?param1=aaa&param2=bbb TODO /?
                post("/test2") {
                    val parameters = call.receiveParameters()

                    val paramVal1 = parameters["param1"]
                    val paramVal2 = parameters["param2"]

                    call.respondText("Test POST request with parameter values $paramVal1 and $paramVal2")
                }

                post("/") {
                    val request = call.receive<Request>()
                    call.respond(request)
                }
            }
        }.start(wait = false) //IMPORTANT Android needs false
    }

    fun stopServer() {
        println("stopServer #")
        // https://dev.to/viniciusccarvalho/graceful-shutdown-of-ktor-applications-1h53
        Runtime.getRuntime().addShutdownHook(Thread {
            server?.stop(1, 1, TimeUnit.SECONDS)
        })
        Thread.currentThread().join()
    }
}
/**************** USE BASH, NOT CMD WINDOWS
curl -X POST http://localhost:8080 -H 'Content-Type: application/json' -d '{ "id" : "someId", "quantity" : 10, "isTrue" : false}'
curl -X POST http://localhost:8080 -d '{ "id" : "someId", "quantity" : 10, "isTrue" : false}'
curl -X POST -d '{ "id" : "someId", "quantity" : 10, "isTrue" : false}' http://localhost:8080
curl -i -X POST http://localhost:8080 -d '{ "id" : "someId", "quantity" : 10, "isTrue" : false}'
curl -i -X POST -d '{ "id" : "someId", "quantity" : 10, "isTrue" : false}' http://localhost:8080
 **************** */