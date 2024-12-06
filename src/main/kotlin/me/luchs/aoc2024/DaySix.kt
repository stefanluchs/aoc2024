package me.luchs.aoc2024

data class DaySix(val input: String) {

    val matrix = input.toCharMatrix()
    val dimensions = matrix.dimensions()

    fun partOne(): Int {
        val start = matrix.entry('^')

        var current = start to Direction.UP
        val positions: MutableList<Pair<Pair<Int, Int>, Direction>> = mutableListOf()

        while (current !in positions) {

            if (current.first.first !in dimensions.first || current.first.second !in dimensions.second) break

            positions.add(current)

            val (position, direction) = current

            val next: Pair<Int, Int> = position.first + direction.row to position.second + direction.column
            val nextValue = matrix.valueOf(next)

            if ('#' == nextValue) {
                val nextDirection = direction.turnRight()
                current = position to nextDirection
            } else {
                current = next to direction
            }

        }

        val distinct = positions.map { it.first }.distinct()

        return distinct.size
    }

}
