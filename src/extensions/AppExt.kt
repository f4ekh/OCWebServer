package com.lutz.bct.extensions

import com.fasterxml.jackson.databind.SerializationFeature
import com.lutz.bct.CourseNotFoundException
import com.lutz.bct.courses
import com.lutz.bct.topCourse
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import java.lang.Compiler.enable


fun Application.installJackson() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT) // Pretty Prints the JSON
        }
    }
}

fun Application.installStatus() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) {
            call.respond(CourseNotFoundException(HttpStatusCode.NotFound.value))
        }
    }
}


fun Application.buildRoutes() {

    routing {
        get("/") {
            call.respondText("Welcome to OpenClassrooms brand new server !", contentType = ContentType.Text.Plain)
        }

        get("/course/{id}") {
            val id = call.parameters["id"]?.toInt() ?: 0
            try {
                call.respond(courses.first { course -> course.id == id })
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("/course/top") { call.respond(topCourse) }
    }

}