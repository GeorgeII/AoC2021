import java.io.File

object Day3 {

    fun part1(file: String): Int {
        val reports = File(file)
            .useLines { it.toList() }

        val mostCommon = (0 until reports[0].length)
            .map { idx ->
                val onesNumber = reports
                    .count { it[idx] == '1' }

                if (onesNumber > reports.size / 2) '1' else '0'
            }
            .joinToString("")

        val gammaRate = mostCommon.toInt(2)
        val epsilon = mostCommon
            .map { if (it == '1') '0' else '1' }
            .joinToString("")
            .toInt(2)

        return gammaRate * epsilon
    }
}
