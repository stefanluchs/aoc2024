package me.luchs.aoc2024

fun Pair<Int, Int>.adjacent(
    numberOfSteps: Int = 1,
    direction: Direction,
    rowRange: IntRange,
    columnRange: IntRange
): List<Pair<Int, Int>> = (0..numberOfSteps)
    .map { steps -> this.first + steps * direction.row to this.second + steps * direction.column }
    .filter { it.first in rowRange && it.second in columnRange }

data class DayFour(val input: String) {

    private val matrix = input.toCharMatrix()

    fun partOne(): Int {

        val (rows, columns) = matrix.dimensions()

        return matrix.entries('X')
            .flatMap { position ->
                Direction.entries.map {
                    position.adjacent(
                        numberOfSteps = 3,
                        direction = it,
                        rowRange = rows,
                        columnRange = columns
                    )
                }
            }
            .filter { it.size == 4 }
            .count {
                matrix[it[0].first][it[0].second] == 'X'
                        && matrix[it[1].first][it[1].second] == 'M'
                        && matrix[it[2].first][it[2].second] == 'A'
                        && matrix[it[3].first][it[3].second] == 'S'
            }

    }

    fun partTwo(): Int {
        val (rows, columns) = matrix.dimensions()
        return matrix.entries('A')
            .filterNot { it.first == 0 || it.second == 0 || it.first == rows.last || it.second == columns.last }
            .map { position ->
                listOf(
                    listOfNotNull(
                        matrix.valueOf(position.first - 1 to position.second - 1),
                        matrix.valueOf(position),
                        matrix.valueOf(position.first + 1 to position.second + 1),
                    ),
                    listOfNotNull(
                        matrix.valueOf(position.first - 1 to position.second + 1),
                        matrix.valueOf(position),
                        matrix.valueOf(position.first + 1 to position.second - 1),
                    ),
                    listOfNotNull(
                        matrix.valueOf(position.first + 1 to position.second - 1),
                        matrix.valueOf(position),
                        matrix.valueOf(position.first - 1 to position.second + 1),
                    ),
                    listOfNotNull(
                        matrix.valueOf(position.first + 1 to position.second + 1),
                        matrix.valueOf(position),
                        matrix.valueOf(position.first - 1 to position.second - 1),
                    )
                ).filter { it.size == 3 }
                    .map { it.joinToString(separator = "") }
                    .filter { it == "MAS" }
            }
            .filter { it.size == 2 }
            .toList()
            .size
    }

}
