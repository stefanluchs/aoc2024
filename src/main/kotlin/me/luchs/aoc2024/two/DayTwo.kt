package me.luchs.aoc2024.two

import kotlin.math.abs


data class DayTwo(val input: String) {

    private val reports = input.lines().map { it.split(" ").map { it.toInt() } }

    fun partOne(): Int = reports
        .map { it.zipWithNext().map { it.second - it.first } }
        .filter { it.all { abs(it) <= 3 } }
        .count { it.all { it < 0 } || it.all { it > 0 } }

}
