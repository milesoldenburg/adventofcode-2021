class Day1 {
    init {
        val inputStream = javaClass.getResourceAsStream("/1/input.txt")
        val measurements: MutableList<Int> = mutableListOf()
        inputStream.bufferedReader().forEachLine { measurements.add(it.toInt()) }

        var increasingMeasurements = 0
        for ((index, measurement) in measurements.withIndex()) {
            if (index > 0 && measurement > measurements[index - 1]) increasingMeasurements++
        }
        println(increasingMeasurements)
    }
}