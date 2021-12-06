import java.io.File

object day1 {

    @JvmStatic
    fun main(args: Array<String>) {
        val depthMeasurements = File("data/inputDay1").useLines { it.toList() }.map { it.toInt() }

        val part1 = depthMeasurements
            .windowed(2)
            .count { it[0] < it[1] }

        println(part1)


        val part2 = depthMeasurements
            .windowed(3)
            .map { it.sum() }
            .windowed(2)
            .count { it[0] < it[1] }

        println(part2)
    }

}