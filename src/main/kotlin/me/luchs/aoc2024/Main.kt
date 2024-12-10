package me.luchs.aoc2024

fun main() {
    val input = readInput("Day9")
    val day = DayNine(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}