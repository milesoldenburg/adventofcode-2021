class Day4 {
    private val drawings = mutableListOf<Int>()
    private val boards = mutableListOf<Array<Array<IntArray>>>()

    init {
        val inputStream = javaClass.getResourceAsStream("/4/input.txt")
        val bufferedReader = inputStream.bufferedReader()

        val firstLine = bufferedReader.readLine()
        for (drawing in firstLine.split(",")) {
            this.drawings.add(drawing.toInt())
        }

        var rowIndex = 0
        var board: Array<Array<IntArray>> = Array(5) { Array(5) { IntArray(2) } }
        bufferedReader.forEachLine {
            if (it == "") {
                rowIndex = 0
                board = Array(5) { Array(5) { IntArray(2) } }
            } else {
                val spaces = it.trim().split(Regex("\\s+"))
                for (i in 0..4) {
                    board[rowIndex][i] = intArrayOf(spaces[i].toInt(), 0)
                }
                if (rowIndex == 4) this.boards.add(board)
                rowIndex++
            }
        }

        //val part1Value = part1()
        val part2Value = part2()

        //println("Part 1: $part1Value")
        println("Part 2: $part2Value")
    }

    private fun part1(): Int {
        var winningDraw = 0
        val winningBoards = mutableListOf<Array<Array<IntArray>>>()
        var hasFoundWinner = false

        while (!hasFoundWinner) {
            winningBoards.clear()
            val drawing = this.drawings.removeFirst()

            for (board in this.boards) {
                for (row in board) {
                    for ((spaceIndex, space) in row.withIndex()) {
                        if (space[0] == drawing) {
                            space[1] = 1
                            if (didRowWin(row) || didColumnWin(board, spaceIndex)) {
                                winningBoards.add(board)
                                hasFoundWinner = true
                            }
                        }
                    }
                }
            }

            if (winningBoards.isNotEmpty()) {
                this.boards.removeAll(winningBoards)
                winningDraw = drawing
            }
        }

        return computeBoardScore(winningBoards.first(), winningDraw)
    }

    private fun part2(): Int {
        var winningDraw = 0
        val winningBoards = mutableListOf<Array<Array<IntArray>>>()

        while (this.boards.isNotEmpty()) {
            winningBoards.clear()
            val drawing = this.drawings.removeFirst()

            for (board in this.boards) {
                for (row in board) {
                    for ((spaceIndex, space) in row.withIndex()) {
                        if (space[0] == drawing) {
                            space[1] = 1
                            if (didRowWin(row) || didColumnWin(board, spaceIndex)) {
                                winningBoards.add(board)
                            }
                        }
                    }
                }
            }

            if (winningBoards.isNotEmpty()) {
                this.boards.removeAll(winningBoards)
                winningDraw = drawing
            }
        }

        return computeBoardScore(winningBoards.last(), winningDraw)
    }

    private fun didRowWin(row: Array<IntArray>): Boolean {
        val sum = row.fold(0) { acc, next -> acc + next[1] }
        return sum == 5
    }

    private fun didColumnWin(board: Array<Array<IntArray>>, columnIndex: Int): Boolean {
        val sum = board.fold(0) { acc, next -> acc + next[columnIndex][1] }
        return sum == 5
    }

    private fun computeBoardScore(winningBoard: Array<Array<IntArray>>, winningDraw: Int): Int {
        var unmarkedBoardSum = 0
        for (row in winningBoard) {
            for (space in row) {
                if (space[1] == 0) unmarkedBoardSum += space[0]
            }
        }

        return unmarkedBoardSum * winningDraw
    }
}