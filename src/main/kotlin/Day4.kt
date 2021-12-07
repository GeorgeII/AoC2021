import java.io.File

object Day4 {

    fun part1(file: String): Int {
        val input = File(file)
            .useLines { it.toList() }

        val numbers = input[0]
            .split(",")
            .map { it.toInt() }

        val boardSize = input[2].split(" ").size

        data class MutPair(val num: Int, var checked: Boolean)

        val boards = input
            .drop(2)
            .filterNot { it.isBlank() }
            .windowed(boardSize, boardSize)
            .map { board ->
                board.map { row ->
                    row.split(" ")
                        .filterNot { it.isBlank() }
                        .map { MutPair(it.toInt(), false) }
                        .toTypedArray()
                }
            }

        fun checkIfWin(board: List<Array<MutPair>>): Boolean {
            var isRowWin = false
            for (row in board) {
                isRowWin = isRowWin || row.all { it.checked }
            }

            var isColWin = false
            for (i in board.indices) {
                val col = board.map { it[i] }
                isColWin = isColWin || col.all { it.checked }
            }

            return isRowWin || isColWin
        }

        fun calcRes(board: List<Array<MutPair>>): Int {
            return board.sumOf { rows ->
                rows
                    .filterNot { it.checked }
                    .map { it.num }
                    .sum()
            }
        }


        var currLot = 5
        while (currLot < numbers.size) {
            val fiveNums = numbers.subList(currLot - 5, currLot)

            for (num in fiveNums) {
                for (board in boards) {
                    for (cols in board) {
                        for (el in cols) {
                            if (el.num == num) {
                                el.checked = true
                            }
                        }
                    }
                }

                for (board in boards) {
                    if (checkIfWin(board)) {
                        return calcRes(board) * num
                    }
                }
            }

            currLot += 5
        }

        return -1
    }


    fun part2(file: String): Int {
        val input = File(file)
            .useLines { it.toList() }

        val numbers = input[0]
            .split(",")
            .map { it.toInt() }

        val boardSize = input[2].split(" ").size

        data class MutPair(val num: Int, var checked: Boolean)

        val boards = input
            .drop(2)
            .filterNot { it.isBlank() }
            .windowed(boardSize, boardSize)
            .map { board ->
                board.map { row ->
                    row.split(" ")
                        .filterNot { it.isBlank() }
                        .map { MutPair(it.toInt(), false) }
                        .toTypedArray()
                }
            }

        fun checkIfWin(board: List<Array<MutPair>>): Boolean {
            var isRowWin = false
            for (row in board) {
                isRowWin = isRowWin || row.all { it.checked }
            }

            var isColWin = false
            for (i in board.indices) {
                val col = board.map { it[i] }
                isColWin = isColWin || col.all { it.checked }
            }

            return isRowWin || isColWin
        }

        fun calcRes(board: List<Array<MutPair>>): Int {
            return board.sumOf { rows ->
                rows
                    .filterNot { it.checked }
                    .map { it.num }
                    .sum()
            }
        }


        val boardsWon = BooleanArray(boards.size) { false }
        var currLot = 5
        while (currLot < numbers.size) {
            val fiveNums = numbers.subList(currLot - 5, currLot)

            for (num in fiveNums) {
                for (board in boards) {
                    for (cols in board) {
                        for (el in cols) {
                            if (el.num == num) {
                                el.checked = true
                            }
                        }
                    }
                }

                for ((idx, board) in boards.withIndex()) {
                    if (checkIfWin(board)) {
                        if (boardsWon.filterIndexed { index, b ->  index != idx}.all { it }) {
                            return calcRes(board) * num
                        }
                        else boardsWon[idx] = true
                    }
                }
            }

            currLot += 5
        }

        return -1
    }

}