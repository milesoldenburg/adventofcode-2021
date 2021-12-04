class Day4 {
    private val drawings = mutableListOf<Int>()
    private val boards = mutableListOf<Array<IntArray>>()

    init {
        val inputStream = javaClass.getResourceAsStream("/4/example.txt")
        val bufferedReader = inputStream.bufferedReader()

        val firstLine = bufferedReader.readLine()
        for (drawing in firstLine.split(",")) {
            drawings.add(drawing.toInt())
        }

        var rowIndex = 0
        var board: Array<IntArray> = Array(5) { IntArray(5) }
        bufferedReader.forEachLine {
            if (it == "") {
                rowIndex = 0
                board = Array(5) { IntArray(5) }
            } else {
                val spaces = it.trim().split(Regex("\\s+"))
                for (i in 0..4) {
                    board[rowIndex][i] = spaces[i].toInt()
                }
                if (rowIndex == 4) boards.add(board)
                rowIndex++
            }
        }
    }
}