fun main(args: Array<String>) {
    println("Running Advent Day ${args[0]}")
    Class.forName("Day${args[0]}").getDeclaredConstructor().newInstance()
}