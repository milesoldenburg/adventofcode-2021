class Day9 {
    private val heightmap = mutableListOf<List<Int>>()
    private var lastRow = 0
    private var lastColumn = 0

    init {
        val inputStream = javaClass.getResourceAsStream("/9/input.txt")
        inputStream.bufferedReader().forEachLine { line ->
            val row = mutableListOf<Int>()
            line.forEach { row.add(Character.getNumericValue(it)) }
            this.heightmap.add(row)
            this.lastColumn = row.count() - 1
        }

        this.lastRow = this.heightmap.count() - 1

        val part1Value = part1()
        println("Part 1: $part1Value")
    }

    private fun part1(): Int {
        val lowPoints = mutableListOf<Int>()
        this.heightmap.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, height ->
                if (isTop(rowIndex) && isLeft(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex + 1], heightmap[rowIndex + 1][columnIndex])) lowPoints.add(height)
                } else if (isTop(rowIndex) && isRight(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex - 1], heightmap[rowIndex + 1][columnIndex])) lowPoints.add(height)
                } else if (isBottom(rowIndex) && isRight(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex - 1][columnIndex], heightmap[rowIndex][columnIndex - 1])) lowPoints.add(height)
                } else if (isBottom(rowIndex) && isLeft(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex - 1][columnIndex], heightmap[rowIndex][columnIndex + 1])) lowPoints.add(height)
                } else if (isTop(rowIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex + 1], heightmap[rowIndex + 1][columnIndex], heightmap[rowIndex][columnIndex - 1])) lowPoints.add(height)
                } else if (isRight(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex - 1], heightmap[rowIndex + 1][columnIndex], heightmap[rowIndex - 1][columnIndex])) lowPoints.add(height)
                } else if (isBottom(rowIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex + 1], heightmap[rowIndex - 1][columnIndex], heightmap[rowIndex][columnIndex - 1])) lowPoints.add(height)
                } else if (isLeft(columnIndex)) {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex + 1], heightmap[rowIndex - 1][columnIndex], heightmap[rowIndex + 1][columnIndex])) lowPoints.add(height)
                } else {
                    if (isLowPoint(height, heightmap[rowIndex][columnIndex + 1], heightmap[rowIndex][columnIndex - 1], heightmap[rowIndex - 1][columnIndex], heightmap[rowIndex + 1][columnIndex])) lowPoints.add(height)
                }
            }
        }
        return lowPoints.fold(0) { acc, next -> acc + next + 1 }
    }

    private fun isTop(rowIndex: Int): Boolean {
        return rowIndex == 0
    }

    private fun isRight(columnIndex: Int): Boolean {
        return columnIndex == this.lastColumn
    }

    private fun isBottom(rowIndex: Int): Boolean {
        return rowIndex == this.lastRow
    }

    private fun isLeft(columnIndex: Int): Boolean {
        return columnIndex == 0
    }

    private fun isLowPoint(height: Int, vararg adjacencies: Int): Boolean {
        return adjacencies.all { it > height }
    }
}