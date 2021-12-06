import java.io.File

object Day1 {

    fun part1(file: String): Int {
        val depthMeasurements = File(file)
            .useLines { it.toList() }
            .map { it.toInt() }

        val res = depthMeasurements
            .windowed(2)
            .count { it[0] < it[1] }

        return res
    }

    fun part2(file: String): Int {
        val depthMeasurements = File(file)
            .useLines { it.toList() }
            .map { it.toInt() }

        val res = depthMeasurements
            .windowed(3)
            .map { it.sum() }
            .windowed(2)
            .count { it[0] < it[1] }

        return res
    }

}