package me.luchs.aoc2024


typealias CharMatrix = Array<CharArray>

fun String.toCharMatrix(): CharMatrix = this.lines().map { it.toCharArray() }.toTypedArray()

fun CharMatrix.dimensions(): Pair<IntRange, IntRange> = indices to 0..<this[0].size

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

fun Pair<Int, Int>.row() = this.first

fun Pair<Int, Int>.column() = this.second
