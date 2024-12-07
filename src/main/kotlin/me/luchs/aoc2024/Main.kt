package me.luchs.aoc2024

fun main() {
    val input = readInput("Day7")
    val day = DaySeven(input.trimIndent())
    println("Part One: " + day.partOne())
    println("Part Two: " + day.partTwo())
}