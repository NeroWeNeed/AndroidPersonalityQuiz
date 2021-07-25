package github.neroweneed.personalityquiz.data

import kotlinx.serialization.Serializable

@Serializable
data class Question(val prompt: String,val answers: List<Response> = emptyList())
@Serializable
data class Response(val text: String,val points: Set<ResponsePoint> = emptySet())
@Serializable
data class ResponsePoint(val nature: Nature, val points: Int)
enum class Nature {
    UNKNOWN,
    HARDY,
    LONELY,
    BRAVE,
    ADAMANT,
    NAUGHTY,
    BOLD,
    DOCILE,
    RELAXED,
    IMPISH,
    LAX,
    TIMID,
    HASTY,
    SERIOUS,
    JOLLY,
    NAIVE,
    MODEST,
    MILD,
    QUIET,
    BASHFUL,
    RASH,
    CALM,
    GENTLE,
    SASSY,
    CAREFUL,
    QUIRKY
}
@Serializable
data class Aura(val name: String,val description: String)