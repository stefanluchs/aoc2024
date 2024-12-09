package me.luchs.aoc2024

data class DayEight(val input: String) {

    private val matrix = input.toCharMatrix()

    fun partOne(): Int = solve(partTwo = false)

    fun partTwo(): Int = solve(partTwo = true)

    private fun solve(partTwo: Boolean) = matrix.distinctValues()
        .asSequence()
        .filterNot { '.' == it }
        .map { matrix.entries(it).toList() }
        .filter { it.size > 1 }
        .flatMap { antinodesForChar(it, partTwo) }
        .distinct().count()

    private fun antinodesForChar(coordinates: List<Coordinate2D>, partTwo: Boolean): List<Coordinate2D> =
        coordinates.combinations()
            .flatMap { antinodesForPair(it, partTwo) }
            .distinct()

    private fun antinodesForPair(pair: Pair<Coordinate2D, Coordinate2D>, partTwo: Boolean): List<Coordinate2D> {
        val (rows, columns) = matrix.dimensions()

        // construct vector from pair of coordinates
        val vector = pair.second.first - pair.first.first to pair.second.second - pair.first.second

        val antinodes: MutableList<Coordinate2D> = mutableListOf()
        var i = 1
        do {
            // antinodes are always on locations which are described as the vector sum of the pair +- the vector
            val options = listOf(
                pair.second.first + i * vector.first to pair.second.second + i * vector.second,
                pair.first.first - i * vector.first to pair.first.second - i * vector.second
            ).filter { it.first in rows && it.second in columns } // must be within bounds
                .filterNot { it == pair.first }
                .filterNot { it == pair.second }
            antinodes.addAll(options)
            i++
        } while (partTwo && options.isNotEmpty())

        return if (partTwo) {
            antinodes + pair.first + pair.second
        } else {
            antinodes
        }
    }

}
