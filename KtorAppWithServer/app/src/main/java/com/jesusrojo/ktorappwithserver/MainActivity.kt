package com.jesusrojo.ktorappwithserver

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080Test2
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080TestJson
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080TestRawData
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080TestResponse
import com.jesusrojo.ktorappwithserver.KtorServer.Companion.localhos8080TestString
import io.ktor.server.engine.*


class MainActivity : AppCompatActivity() {

    private var isClicked = false

    private lateinit var ktorServer:KtorServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        ktorServer = KtorServer()
    }

    private fun initUi() {
        val button: Button = findViewById(R.id.btn_run_server)
        button.setOnClickListener {
            if (isClicked) {
                ktorServer.stopServer()
                button.text = "Start Server"
            } else {
                ktorServer.startServer()
                button.text = "Stop Server"

            }
            isClicked = !isClicked
        }
        val button00: Button = findViewById(R.id.btn_00)
        button00.text = localhos8080
        button00.setOnClickListener { makeIntent(localhos8080) }

        val button01: Button = findViewById(R.id.btn_01)
        button01.text =  localhos8080TestJson
        button01.setOnClickListener {makeIntent(localhos8080TestJson) }

        val button02: Button = findViewById(R.id.btn_02)
        button02.text = localhos8080TestResponse
        button02.setOnClickListener { makeIntent(localhos8080TestResponse) }

        val button03: Button = findViewById(R.id.btn_03)
        button03.text = localhos8080TestString
        button03.setOnClickListener { makeIntent(localhos8080TestString) }

        val button04: Button = findViewById(R.id.btn_04)
        button04.text = localhos8080TestRawData
        button04.setOnClickListener { makeIntent(localhos8080TestRawData) }

        val button05: Button = findViewById(R.id.btn_04)
        button05.text = localhos8080Test2
        button05.setOnClickListener { makeIntent(localhos8080Test2) }
    }

    private fun makeIntent(url: String) {
        val finalUrl = "http://www.$url"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(finalUrl)
        startActivity(i)
    }

//
//    private fun runServer() {
//
//        server = embeddedServer(Netty, 8080) {
//            routing {
//
//                install(StatusPages) {
//                    exception<Throwable> { e ->
//                        call.respondText(e?.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
//                    }
//                }
//
//                // GSON
//                install(ContentNegotiation) {
//                    gson {}
//                }
//
//                get("/") {
//                    val random = (0..100).random()
//                    call.respondText("RANDOM: $random", ContentType.Text.Html)
//                }
//
//                post("/") {
//                    val request = call.receive<Request>()
//                    call.respond(request)
//                }
//
//                get("/test") {
//                    val response = getFakeJsonResponse()
//                    call.respondText(response)
//                }
//
//                get("/test-json") {
//                    call.respondText(getFakeJson(), ContentType.Application.Json)
//                }
//
//            }
//        }.start(wait = false) // false //https://stackoverflow.com/questions/62500669/ktor-android-local-http-server-throws-error
//    }
//
//
//
//    private fun stopServer() {
//        // https://dev.to/viniciusccarvalho/graceful-shutdown-of-ktor-applications-1h53
//        Runtime.getRuntime().addShutdownHook(Thread {
//            server?.stop(1, 1, TimeUnit.SECONDS)
//        })
//        Thread.currentThread().join()
//    }

}