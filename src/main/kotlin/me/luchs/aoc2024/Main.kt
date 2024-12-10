package me.luchs.aoc2024

fun main() {
    val input = readInput("Day10")
    val day = DayTen(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}