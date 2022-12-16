fun main() {
    val input = readInput("Day08")
    val forest = input.map { it.map { l -> l.digitToInt() } }
    part1(forest)
    part2(forest)
}

private fun part1(forest: List<List<Int>>) {
    var visibleTrees = 0
    for ((rowIndex, row) in forest.withIndex()) {
        if (rowIndex == 0 || rowIndex == forest.lastIndex) { visibleTrees += row.size; continue; }

        for ((columnIndex, height) in row.withIndex()) {
            val isOnEdge = columnIndex == 0 || columnIndex == row.lastIndex
            val isTallerThanAllToLeft = row.take(columnIndex).all { it < height }
            val isTallerThanAllToRight = row.takeLast(row.size - columnIndex - 1).all { it < height }
            val isTallerThanAllUp = forest.take(rowIndex).all { it[columnIndex] < height }
            val isTallerThanAllDown = forest.takeLast(forest.size - rowIndex - 1).all { it[columnIndex] < height }
            if (isOnEdge
                || isTallerThanAllToLeft || isTallerThanAllToRight
                || isTallerThanAllUp || isTallerThanAllDown
            ) {
                visibleTrees++
            }
        }
    }

    println(visibleTrees)
}

private fun part2(forest: List<List<Int>>) {
    val scenicScores = mutableListOf<Int>()
    for ((rowIndex, row) in forest.withIndex()) {
        if (rowIndex == 0 || rowIndex == forest.lastIndex) { continue; }
        for ((columnIndex, height) in row.withIndex()) {
            if (columnIndex == 0 || columnIndex == row.lastIndex) continue
            val treesSeenToLeft = row.take(columnIndex).reversed().indexOfFirst { it >= height }.let { return@let if (it == -1) columnIndex else it + 1 }
            val treesSeenToRight = row.takeLast(row.size - columnIndex - 1).indexOfFirst { it >= height }.let { return@let if (it == -1) row.size - columnIndex - 1 else it + 1 }
            val treesSeenToUp = forest.take(rowIndex).reversed().indexOfFirst { it[columnIndex] >= height }.let { return@let if (it == -1)  rowIndex else it + 1 }
            val treesSeenToDown = forest.takeLast(forest.size - rowIndex - 1).indexOfFirst { it[columnIndex] >= height }.let { return@let if (it == -1)  forest.size - rowIndex - 1 else it + 1 }
            scenicScores += treesSeenToLeft * treesSeenToRight * treesSeenToUp * treesSeenToDown
        }
    }

    println(scenicScores.max())
}