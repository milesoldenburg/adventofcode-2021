class Day5 {
    private val segments = mutableListOf<Array<IntArray>>()

    init {
        val inputStream = javaClass.getResourceAsStream("/5/input.txt")

        var width = 0
        var height = 0

        inputStream.bufferedReader().forEachLine {
            val segment: List<String> = it.split(" -> ")

            val segmentStart = segment[0].split(",")
            val x1 = segmentStart[0].toInt()
            val y1 = segmentStart[1].toInt()
            if (x1 > width) width = x1
            if (y1 > height) height = y1

            val segmentEnd = segment[1].split(",")
            val x2 = segmentEnd[0].toInt()
            val y2 = segmentEnd[1].toInt()
            if (x2 > width) width = x2
            if (y2 > height) height = y2

            this.segments.add(
                arrayOf(
                    intArrayOf(x1, y1),
                    intArrayOf(x2, y2)
                )
            )
        }

        val grid = Array(height + 1) { IntArray(width + 1) }

        val part1Value = part1(grid)

        println("Part 1: $part1Value")
    }

    private fun part1(grid: Array<IntArray>): Int {
        for (segment in this.segments) {
            val x1 = segment[0][0]
            val y1 = segment[0][1]
            val x2 = segment[1][0]
            val y2 = segment[1][1]

            if (x1 == x2) {
                if (y1 <= y2) {
                    for (i in y1..y2) {
                        grid[i][x1]++
                    }
                } else {
                    for (i in y1 downTo y2) {
                        grid[i][x1]++
                    }
                }
            }

            if (y1 == y2) {
                if (x1 <= x2) {
                    for (i in x1..x2) {
                        grid[y1][i]++
                    }
                } else {
                    for (i in x1 downTo x2) {
                        grid[y1][i]++
                    }
                }
            }
        }

        var dangerousAreas = 0
        for (row in grid) {
            for (column in row) {
                if (column >= 2) dangerousAreas++
            }
        }

        return dangerousAreas
    }
}