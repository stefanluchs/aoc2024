package me.luchs.aoc2024

fun main() {
    val input = readInput("Day11")
    val day = DayEleven(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}