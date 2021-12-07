class Day6 {
    private val population = LongArray(9)

    init {
        val inputStream = javaClass.getResourceAsStream("/6/input.txt")
        val firstLine = inputStream.bufferedReader().readLine()
        for (age in firstLine.split(",")) {
            population[age.toInt()]++
        }

//        val part1Value = part1(80)
//        println("Part 1: $part1Value")

        val part2Value = part1(256)
        println("Part 2: $part2Value")
    }

    private fun part1(days: Int): Long {
        for (day in 1..days) {
            val previousPopulation = this.population.clone()
            this.population[8] = previousPopulation[0]
            this.population[7] = previousPopulation[8]
            this.population[6] = previousPopulation[0] + previousPopulation[7]
            for (i in 5 downTo 0) {
                this.population[i] = previousPopulation[i + 1]
            }
        }

        return this.population.sum()
    }
}