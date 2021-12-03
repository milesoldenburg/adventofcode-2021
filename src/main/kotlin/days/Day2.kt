class Day2 {
    private val measurements = mutableListOf<Pair<String, Int>>()

    init {
        val inputStream = javaClass.getResourceAsStream("/2/input.txt")
        inputStream.bufferedReader().forEachLine {
            val measurement: List<String> = it.split(' ')
            this.measurements.add(Pair(measurement[0], measurement[1].toInt()))
        }

        val part1Value = part1()
        val part2Value = part2()

        println("Part 1: $part1Value")
        println("Part 2: $part2Value")
    }

    fun part1(): Int{
        var horizontalDistance = 0
        var depth = 0

        for ((direction, units) in this.measurements) {
            when (direction) {
                "forward" -> horizontalDistance += units
                "down" -> depth += units
                "up" -> depth -= units
            }
        }

        return horizontalDistance * depth
    }

    fun part2(): Int{
        var horizontalDistance = 0
        var depth = 0
        var aim = 0

        for ((direction, units) in this.measurements) {
            when (direction) {
                "forward" -> {
                    horizontalDistance += units
                    depth += aim * units
                }
                "down" -> aim += units
                "up" -> aim -= units
            }
        }

        return horizontalDistance * depth
    }
}