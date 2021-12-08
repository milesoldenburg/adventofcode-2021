import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day7 {
    private val positions = mutableListOf<Int>()
    private var minPosition = 0
    private var maxPosition = 0

    init {
        val inputStream = javaClass.getResourceAsStream("/7/input.txt")
        val firstLine = inputStream.bufferedReader().readLine()
        for (position in firstLine.split(",")) {
            val positionValue = position.toInt()
            this.positions.add(positionValue)
            this.minPosition = min(this.minPosition, positionValue)
            this.maxPosition = max(this.maxPosition, positionValue)
        }

        val part1Value = part1()
        println("Part 1: $part1Value")

        val part2Value = part2()
        println("Part 2: $part2Value")
    }

    private fun part1(): Int? {
        val potentialCosts = IntArray((this.maxPosition - this.minPosition) + 1)
        for (i in this.minPosition..this.maxPosition) {
            potentialCosts[i] = this.positions.fold(0) { acc, next -> acc + abs(next - i)}
        }
        return potentialCosts.minOrNull()
    }

    private fun part2(): Int? {
        val potentialCosts = IntArray((this.maxPosition - this.minPosition) + 1)
        for (i in this.minPosition..this.maxPosition) {
            potentialCosts[i] = this.positions.fold(0) { acc, next -> acc + (1..abs(next - i)).sum()}
        }
        return potentialCosts.minOrNull()
    }
}