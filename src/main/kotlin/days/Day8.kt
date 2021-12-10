import kotlin.streams.toList

class Day8 {
    private val signals = mutableListOf<List<String>>()
    private val outputs = mutableListOf<List<String>>()

    init {
        val inputStream = javaClass.getResourceAsStream("/8/example.txt")
        inputStream.bufferedReader().forEachLine {
            val halves = it.split(" | ")
            signals.add(halves[0].split(" "))
            outputs.add(halves[1].split(" "))
        }

        val part1Value = part1()
        println("Part 1: $part1Value")

        val part2Value = part2()
        println("Part 2: $part2Value")
    }

    private fun part1(): Int {
        return this.outputs.fold(0) {
            acc, next ->
            acc + next.filter { intArrayOf(2, 3, 4, 7).contains(it.length) }.count()
        }
    }

    private fun part2(): Int {
        for (segment in this.signals.zip(this.outputs)) {
            val combined = (segment.first + segment.second).map {
                it.toCharArray().sorted().joinToString("")
            }
            val segment1 = combined.find { it.length == 2 }
            val segmentValues = mutableMapOf(
                segment1 to 1,
                combined.find { it.length == 3 } to 7,
                combined.find { it.length == 4 } to 4,
                combined.find { it.length == 7 } to 8
            )
            val segments069 = combined.filter { it.length == 6 }.distinct()
            val segment6 = segments069.find { it.chars().toList().containsAll(segment1!!.chars().toList()) }
            val segments09 = (segments069 as ArrayList).remove(segment6)

            // 6 segments can be 0, 6, 9
            // 6 = 6 length segment not containing segment 1
            // 5 segments can be 2, 3, 5
            // 3 = 5 length segment containing 1 segment
            // a = diff(1, 7)
        }

        return 0
    }
}