package me.luchs.aoc2024.two

import kotlin.math.abs


data class DayTwo(val input: String) {

    private val reports = input.lines().map { it.split(" ").map { it.toInt() } }

    fun partOne(): Int = reports.count { it.isSafe() }

    fun partTwo(): Int {
        val safe = reports.count { it.isSafe() }
        val unsafe = reports.filterNot { it.isSafe() }
        val safeAfter = unsafe.filter { report ->
            report.indices.any {
                val reducedReport = report.toMutableList()
                reducedReport.removeAt(it)
                reducedReport.isSafe()
            }
        }

        return safe + safeAfter.count()
    }


    private fun List<Int>.isSafe(): Boolean {
        val pairs = this.zipWithNext().map { it.second - it.first }
        return pairs.all { abs(it) <= 3 } && (pairs.all { it < 0 } || pairs.all { it > 0 })
    }

}
