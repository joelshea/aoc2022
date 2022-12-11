fun main() {
    val input = readInput("Day07")
    val topDirectory = part1(input)
    part2(topDirectory)
}

sealed interface Content {
    val name: String
    fun size(): Int
}

class Directory(override val name: String) : Content {
    val content = mutableListOf<Content>()

    fun findSubdirectory(name: String): Directory? {
        for (content in content.filterIsInstance<Directory>()) {
            if (content.name == name) {
                return content
            } else {
                val subdirectory = content.findSubdirectory(name)
                if (subdirectory != null) return subdirectory
            }
        }
        return null
    }

    fun findDirectoriesUnderSize(size: Int): List<Directory> {
        val response = mutableListOf<Directory>()
        for (directory in content.filterIsInstance<Directory>()) {
            if (directory.size() < size) {
                response.add(directory)
            }
            response.addAll(directory.findDirectoriesUnderSize(size))
        }
        return response
    }

    fun findDirectoriesOverSize(size: Int): List<Directory> {
        val response = mutableListOf<Directory>()
        for (directory in content.filterIsInstance<Directory>()) {
            if (directory.size() >= size) {
                response.add(directory)
            }
            response.addAll(directory.findDirectoriesOverSize(size))
        }
        return response
    }

    override fun size(): Int {
        return content.sumOf { it.size() }
    }

    override fun toString(): String {
        return "$name ${size()}"
    }
}

class File(override val name: String, private val size: Int) : Content {
    override fun size(): Int {
        return this.size
    }
}

private fun part1(input: List<String>): Directory {
    val topDirectory = Directory("/")
    var currentDirectory = topDirectory
    var x = 0
    while (x < input.size) {
        val line = input[x]
        if (line.startsWith("$")) {
            val parts = line.split(" ")
            when (parts[1]) {
                "cd" -> {
                    val directoryName = parts[2]
                    currentDirectory = when (directoryName) {
                        "/" -> topDirectory // Because it's hard to account for this one edge case
                        ".." -> {
                            val oneDirUpName = currentDirectory.name.substring(
                                0,
                                currentDirectory.name.lastIndexOf("/", currentDirectory.name.lastIndex - 1) + 1
                            )
                            if (oneDirUpName == "/") topDirectory else
                                topDirectory.findSubdirectory(oneDirUpName)
                                    ?: throw Exception("Directory up not found: $oneDirUpName")
                        }
                        else -> currentDirectory.findSubdirectory("${currentDirectory.name}$directoryName/")
                            ?: throw Exception("Subdirectory not found: \"${currentDirectory.name}$directoryName/\"")
                    }
                }

                "ls" -> {
                    val sublist = input.subList(x + 1, input.size).takeWhile { !it.contains("$") }
                    for (contentText in sublist) {

                        val newParts = contentText.split(" ")
                        val content = if (newParts[0] == "dir") {
                            Directory("${currentDirectory.name}${newParts[1]}/")
                        } else {
                            File(newParts[1], newParts[0].toInt())
                        }

                        currentDirectory.content.add(content)
                    }

                    x += sublist.size
                }
            }
        }
        x++
    }

    val smallDirectories = topDirectory.findDirectoriesUnderSize(100000)
    println(smallDirectories.sumOf { it.size() })

    return topDirectory
}

private fun part2(topDirectory: Directory) {
    val currentUsedSpace = 70000000 - topDirectory.size()
    val spaceNeeded = 30000000 - currentUsedSpace
    val bigDirectories = topDirectory.findDirectoriesOverSize(spaceNeeded).run { this.sortedBy { it.size() } }
    println(bigDirectories.first().size())
}