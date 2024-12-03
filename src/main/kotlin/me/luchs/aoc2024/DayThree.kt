package me.luchs.aoc2024

data class DayThree(val input: String) {

    private val mul = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()

    fun partOne(): Int = mul.findAll(input).multiplyAndAddUp()

    fun partTwo(): Int {
        val doStatements = "do\\(\\)".toRegex().findAll(input).indices()
        val excludes = "don't\\(\\)".toRegex().findAll(input).indices()
            .map { dont -> dont.rangeTo(doStatements.first { it > dont }) }
        return mul.findAll(input).filterNot { mul -> excludes.any { it.contains(mul.range.first) } }.multiplyAndAddUp()
    }

    private fun Sequence<MatchResult>.indices() = this.map { it.range.first }.sorted().toList()

    private fun Sequence<MatchResult>.multiplyAndAddUp() = this
        .map { it.value.drop(4).dropLast(1).split(",").map { it.toInt() }.reduce { acc, i -> acc * i } }
        .sum()
}
