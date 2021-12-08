import java.io.File

object Day2 {

    data class Position(val horizontal: Int, val vertical: Int)
    data class AimedPosition(val aim: Int, val horizontal: Int, val vertical: Int)

    fun part1(file: String): Int {
        val course = File(file)
            .useLines { it.toList() }
            .map { it.split(" ") }
            .map { Pair(it[0], it[1].toInt()) }

        val finalPosition = course
            .fold(Position(0, 0)) { acc, (command, dist) ->
                when (command) {
                    "forward" -> acc.copy(horizontal = acc.horizontal + dist)
                    "down" -> acc.copy(vertical = acc.vertical + dist)
                    "up" -> acc.copy(vertical = acc.vertical - dist)
                    else -> acc
                }
            }

        return finalPosition.horizontal * finalPosition.vertical
    }

    fun part2(file: String): Int {
        val course = File(file)
            .useLines { it.toList() }
            .map { it.split(" ") }
            .map { Pair(it[0], it[1].toInt()) }

        val finalPosition = course
            .fold(AimedPosition(0, 0, 0)) { acc, (command, dist) ->
                when (command) {
                    "forward" -> acc.copy(horizontal = acc.horizontal + dist, vertical = acc.vertical + acc.aim * dist)
                    "down" -> acc.copy(aim = acc.aim + dist)
                    "up" -> acc.copy(aim = acc.aim - dist)
                    else -> acc
                }
            }

        return finalPosition.horizontal * finalPosition.vertical
    }
}
