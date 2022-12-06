fun main() {
    val input = readInput("Day1")
    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    var largest = 0
    var current = 0
    for (calories in input) {
        if (calories.isBlank()) {
            if (current > largest) largest = current
            current = 0
        } else {
            current += calories.toInt()
        }
    }
    println(largest)
}

private fun part2(input: List<String>) {
    var largest = mutableListOf(0, 0, 0)
    var current = 0
    for (calories in input) {
        if (calories.isBlank()) {
            largest.add(current)
            largest = largest.sortedDescending().toMutableList().apply { this.removeLast() }
            current = 0
        } else {
            current += calories.toInt()
        }
    }
    println(largest.sum())
}