fun main() {
    val input = readInput("Day06")
    part1(input[0])
    part2(input[0])
}

private fun part1(input: String) {
    val markerSize = 4
    var x = markerSize
    val windows = input.windowed(markerSize,1)

    for (window in windows) {
        if (window.toSet().size == markerSize) break
        x++
    }

    println(x)
}

private fun part2(input: String) {
    val markerSize = 14
    var x = markerSize
    val windows = input.windowed(markerSize,1)

    for (window in windows) {
        if (window.toSet().size == markerSize) break
        x++
    }

    println(x)
}