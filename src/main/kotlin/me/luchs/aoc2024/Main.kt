package me.luchs.aoc2024

import me.luchs.aoc2024.two.DayTwo

fun main() {
    val input = readInput("Day2")
    val day = DayTwo(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}