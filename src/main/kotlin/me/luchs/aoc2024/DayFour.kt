package me.luchs.aoc2024

typealias CharMatrix = Array<CharArray>

fun String.toCharMatrix(): CharMatrix = this.lines().map { it.toCharArray() }.toTypedArray()

fun CharMatrix.dimensions(): Pair<IntRange, IntRange> = indices to 0..<this[0].size

fun Pair<Int, Int>.row() = this.first

fun Pair<Int, Int>.column() = this.second

data class DayFour(val input: String) {

    fun partOne(): Int {

        val matrix = input.toCharMatrix()
        val (rows, columns) = matrix.dimensions()

        return matrix.asSequence()
            .mapIndexed { row, line ->
                line.mapIndexed { colum, value -> colum to value }
                    .filter { (_, value) -> value == 'X' }
                    .map { row to it.first }
            }
            .flatten()
            .flatMap { position -> Direction.entries.map { position.adjacent(3, it, rows, columns) } }
            .filter { it.size == 4 }
            .count {
                matrix[it[0].first][it[0].second] == 'X'
                        && matrix[it[1].first][it[1].second] == 'M'
                        && matrix[it[2].first][it[2].second] == 'A'
                        && matrix[it[3].first][it[3].second] == 'S'
            }

    }

    fun Pair<Int, Int>.adjacent(
        numberOfSteps: Int = 1,
        direction: Direction,
        rows: IntRange,
        columns: IntRange
    ): List<Pair<Int, Int>> = (0..numberOfSteps)
        .map { steps -> this.first + steps * direction.row to this.second + steps * direction.column }
        .filter { it.first in rows && it.second in columns }


    fun Pair<Int, Int>.adjacent8(rows: IntRange, columns: IntRange): Array<Pair<Int, Int>> = arrayOf(
        this.first - 1 to this.second, // down
        this.first + 1 to this.second, // up
        this.first to this.second + 1, // right
        this.first to this.second - 1, // left
        this.first + 1 to this.second + 1, // up right
        this.first - 1 to this.second - 1, // down left
        this.first - 1 to this.second + 1, // down right
        this.first + 1 to this.second - 1, // up left
    ).filter { it.first in rows && it.second in columns }.toTypedArray()

    enum class Direction(val row: Int, val column: Int) {
        UP(-1, 0),
        DOWN(1, 0),
        LEFT(0, -1),
        RIGHT(0, 1),
        UP_LEFT(-1, -1),
        UP_RIGHT(-1, 1),
        DOWN_LEFT(1, -1),
        DOWN_RIGHT(1, 1)
    }

}
