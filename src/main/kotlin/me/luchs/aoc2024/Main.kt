package me.luchs.aoc2024

import me.luchs.aoc2024.one.DayOne

fun main() {
    val input = readInput("Day2")
    val day = DayOne(input.trimIndent())
    println("Part One: " + day.partOne())
    //println("Part Two: " + day.partTwo())
}