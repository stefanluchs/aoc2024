package me.luchs.aoc2024

fun main() {
    val input = readInput("Day3")
    val day = DayThree(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}