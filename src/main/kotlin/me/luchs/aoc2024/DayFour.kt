package me.luchs.aoc2024

typealias CharMatrix = Array<CharArray>

fun String.toCharMatrix(): CharMatrix = this.lines().map { it.toCharArray() }.toTypedArray()

fun CharMatrix.dimensions(): Pair<IntRange, IntRange> = indices to 0..<this[0].size

fun CharMatrix.valueOf(position: Pair<Int, Int>): Char = this[position.row()][position.column()]

private fun CharMatrix.entries(c: Char): Sequence<Pair<Int, Int>> = this.asSequence()
    .mapIndexed { row, line ->
        line.mapIndexed { colum, value -> colum to value }
            .filter { (_, value) -> value == c }
            .map { row to it.first }
    }
    .flatten()

fun Pair<Int, Int>.row() = this.first

fun Pair<Int, Int>.column() = this.second

fun Pair<Int, Int>.adjacent(
    direction: Direction,
    rowRange: IntRange,
    columnRange: IntRange
): Pair<Int, Int>? = this.adjacent(1, direction, rowRange, columnRange).drop(1).singleOrNull()

fun Pair<Int, Int>.adjacent(
    numberOfSteps: Int = 1,
    direction: Direction,
    rowRange: IntRange,
    columnRange: IntRange
): List<Pair<Int, Int>> = (0..numberOfSteps)
    .map { steps -> this.first + steps * direction.row to this.second + steps * direction.column }
    .filter { it.first in rowRange && it.second in columnRange }

enum class Direction(val row: Int, val column: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1);

    companion object {
        fun diagonals(): List<Direction> = listOf(UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT)
        fun orthogonal(): List<Direction> = listOf(UP, DOWN, LEFT, RIGHT)
    }
}

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

        val diagonal = matrix.entries('A')
            .map { position ->
                listOf(
                    listOf(Direction.UP_LEFT, Direction.DOWN_RIGHT).mapNotNull { position.adjacent(it, rows, columns)?.let { matrix.valueOf(it) } },
                    listOf(Direction.UP_RIGHT, Direction.DOWN_LEFT).mapNotNull { position.adjacent(it, rows, columns)?.let { matrix.valueOf(it) } },
                ).filter { it.size == 2 }.filter { it.containsAll(listOf('M', 'S')) }
            }.filter { it.size == 2 }
            .toList()

        val orthogonal = matrix.entries('A')
            .map { position ->
                listOf(
                    listOf(Direction.UP, Direction.DOWN).mapNotNull { position.adjacent(it, rows, columns)?.let { matrix.valueOf(it) } },
                    listOf(Direction.LEFT, Direction.RIGHT).mapNotNull { position.adjacent(it, rows, columns)?.let { matrix.valueOf(it) } },
                ).filter { it.size == 2 }.filter { it.containsAll(listOf('M', 'S')) }
            }.filter { it.size == 2 }
            .toList()

        return diagonal.count() + orthogonal.count()
    }

}
