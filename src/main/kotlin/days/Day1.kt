class Day1 {
    private val measurements = mutableListOf<Int>()

    init {
        val inputStream = javaClass.getResourceAsStream("/1/input.txt")
        inputStream.bufferedReader().forEachLine { this.measurements.add(it.toInt()) }

        val part1Value = part1(this.measurements)
        val part2Value = part2()

        println("Part 1: $part1Value")
        println("Part 2: $part2Value")
    }

    fun part1(measurements: MutableList<Int>): Int{
        var increasingMeasurements = 0
        for ((index, measurement) in measurements.withIndex()) {
            if (index > 0 && measurement > measurements[index - 1]) increasingMeasurements++
        }
        return increasingMeasurements
    }

    fun part2(): Int {
        val windows = mutableListOf<Int>()
        for ((index, measurement) in this.measurements.withIndex()) {
            if (index >= 2) windows.add(measurement + this.measurements[index - 1] + this.measurements[index - 2])
        }
        return this.part1(windows)
    }
}