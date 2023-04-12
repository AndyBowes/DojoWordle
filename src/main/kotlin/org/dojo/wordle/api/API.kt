package org.dojo.wordle.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


class API {

    val BaseUrl = "https://yorkcodedojowordleapi.azurewebsites.net"

    @OptIn(ExperimentalSerializationApi::class)
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
    data class CreateTeamResponse(val id: String, val name: String)

    @Serializable
    data class CreateTeamRequest(val name: String)

    @Serializable
    data class CreateGameRequest(val teamId: String)

    @Serializable
    data class CreateGameResponse(val gameId: String)

    @Serializable
    data class MakeGuessRequest(val gameId: String, val guess: String)

    @Serializable
    data class MakeGuessResponse(val score: String, val state: Int)

    suspend fun createTeam(): CreateTeamResponse {
        val response = client.post("$BaseUrl/team") {
            setBody(CreateTeamRequest("Andy and Paul"))
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
        return response.body()
    }

    suspend fun createGame(team: String): CreateGameResponse =
        client.post("$BaseUrl/game") {
            setBody(CreateGameRequest(team))
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body()

    suspend fun makeGuess(gameId: String, word: String): MakeGuessResponse {
        return client.post("$BaseUrl/guess") {
            setBody(MakeGuessRequest(gameId, word))
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }.body()
    }
}
