package me.luchs.aoc2024

data class DayEight(val input: String) {

    private val matrix = input.toCharMatrix()

    fun partOne(): Int {
        return matrix.distinctValues()
            .asSequence()
            .filterNot { '.' == it }
            .map { it to matrix.entries(it).toList() }
            .flatMap { antinodesForPosition(it) }
            .distinct().count()
    }

    private fun antinodesForPosition(position: Pair<Char, List<Coordinate2D>>): List<Coordinate2D> {
        val antinodes = position.second.combinations().flatMap { antinodesForPair(it) }.distinct()
        return antinodes
    }


    private fun antinodesForPair(pair: Pair<Coordinate2D, Coordinate2D>): List<Coordinate2D> {

        val (rows, columns) = matrix.dimensions()

        val vector = pair.second.first - pair.first.first to pair.second.second - pair.first.second

        val options: MutableList<Pair<Int, Int>> = mutableListOf()

        val next = listOf(
            pair.second.first + vector.first to pair.second.second + vector.second,
            pair.first.first - vector.first to pair.first.second - vector.second
        ).filter { it.first in rows && it.second in columns }
        options.addAll(next)

        val antinodes = options.filterNot { it == pair.first }.filterNot { it == pair.second }

        return antinodes
    }

}
