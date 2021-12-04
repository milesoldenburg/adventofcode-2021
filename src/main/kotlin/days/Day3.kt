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
        var oxygenGeneratorRatings = this.measurements
        var bitPosition = 0
        while (oxygenGeneratorRatings.count() > 1) {
            var setBits = 0
            oxygenGeneratorRatings.forEach { if (it[bitPosition] == '1') setBits++ }
            val mostCommonBit = if (setBits >= oxygenGeneratorRatings.count() - setBits) '1' else '0'
            oxygenGeneratorRatings = oxygenGeneratorRatings.filter { it[bitPosition] == mostCommonBit } as MutableList<String>
            bitPosition++
        }

        val oxygenGeneratorRating = Integer.parseInt(oxygenGeneratorRatings.first(), 2)

        var co2ScrubberRatings = this.measurements
        bitPosition = 0
        while (co2ScrubberRatings.count() > 1) {
            var setBits = 0
            co2ScrubberRatings.forEach { if (it[bitPosition] == '1') setBits++ }
            val leastCommonBit = if (setBits < co2ScrubberRatings.count() - setBits) '1' else '0'
            co2ScrubberRatings = co2ScrubberRatings.filter { it[bitPosition] == leastCommonBit } as MutableList<String>
            bitPosition++
        }

        val co2ScrubberRating = Integer.parseInt(co2ScrubberRatings.first(), 2)

        return oxygenGeneratorRating * co2ScrubberRating
    }
}