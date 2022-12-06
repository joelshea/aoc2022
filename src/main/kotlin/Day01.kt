fun main() {
    val input = readInput("Day1")
    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    val total = input.map { it.toInt() }.windowed(2).count { (a, b) -> a < b }
    println(total)
}

private fun part2(input: List<String>) {
    val total = input.map { it.toInt() }.windowed(4).count { it[0] < it[3] }
    println(total)
}