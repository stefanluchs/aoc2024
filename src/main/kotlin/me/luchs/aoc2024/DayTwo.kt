package me.luchs.aoc2024

import kotlin.math.abs


data class DayTwo(val input: String) {

    private val reports = input.lines().map { it.split(" ").map { it.toInt() } }

    fun partOne(): Int = reports.count { it.isSafe() }

    fun partTwo(): Int = reports.count { it.isSafe() } + reports.filterNot { it.isSafe() }
        .count { report -> report.indices.any { report.toMutableList().apply { removeAt(it) }.isSafe() } }

    private fun List<Int>.isSafe(): Boolean {
        val pairs = this.zipWithNext().map { it.second - it.first }
        return pairs.all { abs(it) <= 3 } && (pairs.all { it < 0 } || pairs.all { it > 0 })
    }

}
