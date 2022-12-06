fun main() {
    val input = readInput("Day05")

    part1(input)
    part2(input)
}

private fun part1(input: List<String>) {
    val boxes = Array(9) { Array(100) { "" } }

    var x = 0
    for (line in input.takeWhile { it.isNotEmpty() }.reversed()) {
        if (line[1] == '1') continue

        var y=0
        for (box in line.windowed(3, 4)) {
            boxes[y++][x] = box.substring(1,2)
        }

        x++
    }

    val instructions = input.filter { it.startsWith("move") }
    for (instruction in instructions) {
        val parts = instruction.split(" ")

        val numberToTake = parts[1].toInt()
        val firstStack = parts[3].toInt() - 1
        val secondStack = parts[5].toInt() - 1
        val indexOfTopBoxOnFirstStack = boxes[firstStack].indexOfFirst { it.isBlank() }
        val indexOfLastBoxToTake = indexOfTopBoxOnFirstStack - numberToTake
        val indexOfFirstEmptySpaceOnSecondStack = boxes[secondStack].indexOfFirst { it.isBlank() }

        val boxesToMove = boxes[firstStack].copyOfRange(indexOfLastBoxToTake, indexOfTopBoxOnFirstStack).apply { this.reverse() }

        boxesToMove.copyInto(
            boxes[secondStack],
            indexOfFirstEmptySpaceOnSecondStack
        )

        boxes[firstStack].fill("", indexOfLastBoxToTake, indexOfTopBoxOnFirstStack)

    }



    for (j in 0 .. x) {
        print(boxes[j][boxes[j].indexOfFirst { it.isBlank() } - 1] )
    }

    println()
}

private fun part2(input: List<String>) {
    val boxes = Array(9) { Array(100) { "" } }

    var x = 0
    for (line in input.takeWhile { it.isNotEmpty() }.reversed()) {
        if (line[1] == '1') continue

        var y=0
        for (box in line.windowed(3, 4)) {
            boxes[y++][x] = box.substring(1,2)
        }

        x++
    }

    val instructions = input.filter { it.startsWith("move") }
    for (instruction in instructions) {
        val parts = instruction.split(" ")

        val numberToTake = parts[1].toInt()
        val firstStack = parts[3].toInt() - 1
        val secondStack = parts[5].toInt() - 1
        val indexOfTopBoxOnFirstStack = boxes[firstStack].indexOfFirst { it.isBlank() }
        val indexOfLastBoxToTake = indexOfTopBoxOnFirstStack - numberToTake
        val indexOfFirstEmptySpaceOnSecondStack = boxes[secondStack].indexOfFirst { it.isBlank() }

        val boxesToMove = boxes[firstStack].copyOfRange(indexOfLastBoxToTake, indexOfTopBoxOnFirstStack)

        boxesToMove.copyInto(
            boxes[secondStack],
            indexOfFirstEmptySpaceOnSecondStack
        )

        boxes[firstStack].fill("", indexOfLastBoxToTake, indexOfTopBoxOnFirstStack)

    }



    for (j in 0 .. x) {
        print(boxes[j][boxes[j].indexOfFirst { it.isBlank() } - 1] )
    }

    println()
}