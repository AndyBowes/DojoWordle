import kotlinx.coroutines.runBlocking
import org.dojo.wordle.api.API

fun main() {

    val api = API()
    runBlocking {
        val (_, teamId) = api.createTeam()
        val (gameId) = api.createGame(teamId)

        val response = api.makeGuess(gameId, "arise")

        print(response)
    }
}
