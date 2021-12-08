import java.io.File

object Day5 {

    fun part1(file: String): Int {
        val coords = File(file)
            .useLines { it.toList() }
            .map {
                it.split(" -> ")
                    .map { it.split(",").map { it.toInt() } }
                    .map { Pair(it[0], it[1]) }
            }

        val rowNum = coords
            .map { it.map { it.first } }
            .flatten()
            .maxOrNull() ?: 0

        val colNum = coords
            .map { it.map { it.second } }
            .flatten()
            .maxOrNull() ?: 0

        val bottomMap = Array(rowNum + 1) { IntArray(colNum + 1) { 0 } }

        coords
            .forEach { segment ->
                val (x1, y1) = segment[0]
                val (x2, y2) = segment[1]

                val minX = if (x1 < x2) x1 else x2
                val maxX = if (x1 < x2) x2 else x1
                val minY = if (y1 < y2) y1 else y2
                val maxY = if (y1 < y2) y2 else y1

                if (x1 == x2) {
                    for (i in minY..maxY) {
                        bottomMap[x1][i] += 1
                    }
                } else if (y1 == y2) {
                    for (i in minX..maxX) {
                        bottomMap[i][y2] += 1
                    }
                }
            }

        val res = bottomMap
            .map { row ->
                row.count { it >= 2 }
            }
            .sum()

        return res
    }
}
