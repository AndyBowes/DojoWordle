package org.dojo.wordle.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class API {

    val BaseUrl = "https://yorkcodedojowordleapi.azurewebsites.net"

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                encodeDefaults = false
                explicitNulls = false
            })
        }
    }

    @Serializable
    data class CreateTeamResponse(@SerialName("id") val id: String, @SerialName("name") val name: String)

    @Serializable
    data class CreateTeamRequest(@SerialName("name") val name: String)

    suspend fun createTeam(): CreateTeamResponse {
        val response = client.post("$BaseUrl/team") {
            setBody(CreateTeamRequest("Andy and Paul"))
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return response.body()
    }

    suspend fun sentBestGuess(guess: String) {
        client.post("$BaseUrl/")
    }
}
