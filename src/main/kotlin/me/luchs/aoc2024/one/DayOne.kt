package me.luchs.aoc2024.one

import kotlin.math.abs

data class DayOne(val input: String) {

    private val lists = input.lines().map { row -> row.split("   ").map { it.toInt() } }
    private val left: List<Int> = lists.map { it[0] }
    private val right: List<Int> = lists.map { it[1] }

    fun partOne(): Int = left.sorted().zip(right.sorted()).sumOf { abs(it.first - it.second) }

    fun partTwo(): Int = left.sumOf { it * right.containsTimes(it) }

    private fun <T> List<T>.containsTimes(value: T): Int = this.count { it == value }

}
