fun main() {
    val input = readInput("Day03")
    part1(input)
    part2(input)
}

var value = 1
val letterMap = ('a'..'z').associateWith { value++ } + ('A'..'Z').associateWith { value++ }

private fun part1(input: List<String>) {
    var sumOfPriority = 0
    for (contents in input) {
        val (firstPocket, secondPocket) = contents.chunked( contents.length/2)

        firstPocket.first { secondPocket.contains(it) }.let {
            sumOfPriority += letterMap[it] ?: throw Exception("Where did $it come from?")
        }
    }

    println(sumOfPriority)
}

private fun part2(input: List<String>) {
    var sumOfPriority = 0
    val groups = input.chunked(3)
    for (group in groups) {
        val badge = group[0].filter { group[1].contains(it) }.first() { group[2].contains(it) }
        sumOfPriority += letterMap[badge] ?: throw Exception("Where did $badge come from?")
    }

    println(sumOfPriority)
}