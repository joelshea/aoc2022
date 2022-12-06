fun main() {
    val input = readInput("Day02")
    part1(input)
    part2(input)
}

sealed class HandShape(val value: Int)

object Rock : HandShape(1)
object Paper : HandShape(2)
object Scissors : HandShape(3)

sealed class Outcome(val value: Int)
object Loss : Outcome(0)
object Draw : Outcome(3)
object Win : Outcome(6)


fun judge(them: HandShape, me: HandShape): Outcome {
    return when (them) {
        Rock -> when (me) {
            Paper -> Win; Scissors -> Loss; Rock -> Draw
        }
        Paper -> when (me) {
            Paper -> Draw; Scissors -> Win; Rock -> Loss
        }
        Scissors -> when (me) {
            Paper -> Loss; Scissors -> Draw; Rock -> Win
        }
    }
}

fun getNeededHandShape(them: HandShape, outcome: Outcome): HandShape {
    return when (them) {
        Rock -> when (outcome) {
            Win -> Paper; Loss -> Scissors; Draw -> Rock
        }
        Paper -> when (outcome) {
            Draw -> Paper; Win -> Scissors; Loss -> Rock
        }
        Scissors -> when (outcome) {
            Loss -> Paper; Draw -> Scissors; Win -> Rock
        }
    }
}

val handShapeMap = mapOf(
    "A" to Rock,
    "X" to Rock,
    "B" to Paper,
    "Y" to Paper,
    "C" to Scissors,
    "Z" to Scissors
)

private fun part1(input: List<String>) {
    var total = 0
    for (round in input) {
        val (them, me) = round.split(" ").let { Pair(handShapeMap[it[0]]!!, handShapeMap[it[1]]!!) }
        total += me.value + judge(them, me).value
    }

    println(total)
}

val outcomeMap = mapOf(
    "X" to Loss,
    "Y" to Draw,
    "Z" to Win
)
private fun part2(input: List<String>) {
    var total = 0

    for (round in input) {
        val (them, outcome) = round.split(" ").let { Pair(handShapeMap[it[0]]!!, outcomeMap[it[1]]!!) }
        total += outcome.value + getNeededHandShape(them, outcome).value
    }

    println(total)
}