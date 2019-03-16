package com.lutz.bct

import com.lutz.bct.extensions.buildRoutes
import com.lutz.bct.extensions.installJackson
import com.lutz.bct.extensions.installStatus
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.html.*
import kotlinx.css.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import java.util.*

val topCourse = Course(id = 1, title = "How to troll a Troll ?", level = 5, isActive = false)

val courses = Collections.synchronizedList(
    mutableListOf(
        Course(id = 2, title = "Kotlin for troll", isActive = true),
        Course(id = 3, title = "Kotlin for troll - Part 2"),
        topCourse
    )
)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    val client = HttpClient(Apache) {
    }

    this.buildRoutes()
    this.installJackson()
    this.installStatus()
}