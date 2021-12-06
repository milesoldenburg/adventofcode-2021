import kotlin.math.max
import kotlin.math.min

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

        //val part1Value = part1(grid)
        val part2Value = part2(grid)

        //println("Part 1: $part1Value")
        println("Part 2: $part2Value")
    }

    private fun part1(grid: Array<IntArray>): Int {
        for (segment in this.segments) {
            val x1 = segment[0][0]
            val y1 = segment[0][1]
            val x2 = segment[1][0]
            val y2 = segment[1][1]

            if ((x1 == x2) || (y1 == y2)) {
                for (x in min(x1, x2)..max(x1, x2)) {
                    for (y in min(y1, y2)..max(y1, y2)) {
                        grid[y][x]++
                    }
                }
            }
        }

        return computeDangerousAreas(grid)
    }

    private fun part2(grid: Array<IntArray>): Int {
        for (segment in this.segments) {
            val x1 = segment[0][0]
            val y1 = segment[0][1]
            val x2 = segment[1][0]
            val y2 = segment[1][1]

            if ((x1 == x2) || (y1 == y2)) {
                for (x in min(x1, x2)..max(x1, x2)) {
                    for (y in min(y1, y2)..max(y1, y2)) {
                        grid[y][x]++
                    }
                }
            } else if ((max(x1, x2) - min(x1, x2)) == (max(y1, y2) - min(y1, y2))) {
                var xList: List<Int>
                var yList: List<Int>

                if (x1 <= x2) {
                    xList = (x1..x2).toList()
                } else {
                    xList = (x1 downTo x2).toList()
                }

                if (y1 <= y2) {
                    yList = (y1..y2).toList()
                } else {
                    yList = (y1 downTo y2).toList()
                }

                val xyList = xList.zip(yList)
                for ((x, y) in xyList) {
                    grid[y][x]++
                }
            }
        }

        return computeDangerousAreas(grid)
    }

    private fun computeDangerousAreas(grid: Array<IntArray>): Int {
        var dangerousAreas = 0
        for (row in grid) {
            for (column in row) {
                print(column)
                if (column >= 2) dangerousAreas++
            }
            print('\n')
        }

        return dangerousAreas
    }
}