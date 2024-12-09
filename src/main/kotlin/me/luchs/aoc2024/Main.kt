package me.luchs.aoc2024

fun main() {
    val input = readInput("Day8")
    val day = DayEight(input.trimIndent())
    println("Part One: " + day.partOne())
    //println("Part Two: " + day.partTwo())
}