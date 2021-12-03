class Day3 {
    private val measurements = mutableListOf<String>()

    init {
        val inputStream = javaClass.getResourceAsStream("/3/input.txt")
        inputStream.bufferedReader().forEachLine { this.measurements.add(it) }

        val part1Value = part1()
        val part2Value = part2()

        println("Part 1: $part1Value")
        println("Part 2: $part2Value")
    }

    fun part1(): Int {
        val setBitCounts = IntArray(12)

        this.measurements.forEach {
            for ((index, bit) in it.withIndex()) {
                if (bit == '1') setBitCounts[index]++
            }
        }

        var gammaRate = 0
        setBitCounts.forEachIndexed {
            index, element -> if (element > this.measurements.count() - element) gammaRate = (1 shl 11 - index) or gammaRate
        }
        val mask = 0b111111111111
        val epsilonRate = gammaRate.inv() and mask

        return gammaRate * epsilonRate
    }

    fun part2(): Int {
        return 0
    }
}