fun main() {
    val input = readInput("Day04")
    val assignmentPairs = input.map {
        it.split(',')
            .map { assignment ->
                assignment.split('-')
                    .let { a -> IntRange(a.first().toInt(), a.last().toInt()) }
            }.let { a -> Pair(a.first(), a.last()) }
    }
    part1(assignmentPairs)
    part2(assignmentPairs)
}

private fun part1(assignmentPairs: List<Pair<IntRange, IntRange>>) {
    var total = 0
    for (pair in assignmentPairs) {
        if ((pair.first.first >= pair.second.first && pair.first.last <= pair.second.last) ||
            (pair.second.first >= pair.first.first && pair.second.last <= pair.first.last))
            total++
    }

    println(total)
}

private fun part2(assignmentPairs: List<Pair<IntRange, IntRange>>) {
    var total = 0
    for (pair in assignmentPairs) {
        for(x in pair.first) {
            if (pair.second.contains(x)) {
                total++
                break
            }
        }
    }

    println(total)
}