package me.luchs.aoc2024

data class DayEight(val input: String) {

    private val matrix = input.toCharMatrix()

    fun partOne(): Int =
        matrix.distinctValues()
            .asSequence()
            .filterNot { '.' == it }
            .map { matrix.entries(it).toList() }
            .flatMap { antinodesForChar(it) }
            .distinct().count()

    private fun antinodesForChar(coordinates: List<Coordinate2D>): List<Coordinate2D> =
        coordinates.combinations()
            .flatMap { antinodesForPair(it) }
            .distinct()


    private fun antinodesForPair(pair: Pair<Coordinate2D, Coordinate2D>): List<Coordinate2D> {
        val (rows, columns) = matrix.dimensions()

        // construct vector from pair of coordinates
        val vector = pair.second.first - pair.first.first to pair.second.second - pair.first.second

        // antinodes are always on locations which are described as the vector sum of the pair +- the vector
        val antinodes = listOf(
            pair.second.first + vector.first to pair.second.second + vector.second,
            pair.first.first - vector.first to pair.first.second - vector.second
        )
            .filter { it.first in rows && it.second in columns } // must be within bounds
            .filterNot { it == pair.first }
            .filterNot { it == pair.second }

        return antinodes
    }

}
