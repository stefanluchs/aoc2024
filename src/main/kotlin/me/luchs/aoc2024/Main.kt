package me.luchs.aoc2024

fun main() {
    val input = readInput("Day5")
    val day = DayFive(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}