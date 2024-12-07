package me.luchs.aoc2024

data class DaySeven(val input: String) {

    private val equations = input.lines().map {
        val (testValue, numbers) = it.split(": ")
        Equation(testValue.toLong(), numbers.split(' ').map { it.toLong() })
    }

    fun partOne(): Long = solveWith(operationsPartOne())

    fun partTwo(): Long = solveWith(operationsPartTwo())

    private fun solveWith(operations: List<(Long, Long) -> Long>) =
        equations.filter { it.isTrueWith(operations) }.sumOf { it.testValue }

    private data class Equation(val testValue: Long, val numbers: List<Long>) {

        fun isTrueWith(operators: List<(Long, Long) -> Long>): Boolean {
            var options = listOf(numbers.first())
            numbers.drop(1).forEach { number ->
                options = options.flatMap { option ->
                    operators.map { it.invoke(option, number) }.filter { it <= testValue }
                }
            }
            return options.any { it == testValue }
        }
    }

    private fun operationsPartOne(): List<(Long, Long) -> Long> =
        listOf(
            { a: Long, b: Long -> a + b },
            { a: Long, b: Long -> a * b }
        )

    private fun operationsPartTwo(): List<(Long, Long) -> Long> =
        operationsPartOne() + { a: Long, b: Long -> (a.toString() + b.toString()).toLong() }

}
