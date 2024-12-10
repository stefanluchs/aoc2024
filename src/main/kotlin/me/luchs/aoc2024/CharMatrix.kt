package me.luchs.aoc2024

import kotlin.math.abs


typealias CharMatrix = Array<CharArray>

fun String.toCharMatrix(): CharMatrix = this.lines().map { it.toCharArray() }.toTypedArray()

fun CharMatrix.dimensions(): Pair<IntRange, IntRange> = indices to 0..<this[0].size

fun CharMatrix.distinctValues(): List<Char> = this.flatMap { it.toList() }.distinct()

fun CharMatrix.valueOf(position: Pair<Int, Int>): Char? {
    val (rows, columns) = this.dimensions()
    return if (position.first in rows && position.second in columns) {
        this[position.row()][position.column()]
    } else {
        null
    }
}

fun CharMatrix.entry(c: Char): Pair<Int, Int> = this.entries(c).first()

fun CharMatrix.entries(c: Char): Sequence<Pair<Int, Int>> = this.asSequence()
    .mapIndexed { row, line ->
        line.mapIndexed { colum, value -> colum to value }
            .filter { (_, value) -> value == c }
            .map { row to it.first }
    }
    .flatten()

typealias Coordinate2D = Pair<Int, Int>

fun Coordinate2D.row() = this.first

fun Coordinate2D.column() = this.second

fun Coordinate2D.manhattanDistanceTo(other: Coordinate2D): Int =
    abs(this.first - other.first) + abs(this.second - other.second)

fun Coordinate2D.adjacentFour() = listOf(
    this.first + 1 to this.second,
    this.first - 1 to this.second,
    this.first to this.second + 1,
    this.first to this.second - 1
)
