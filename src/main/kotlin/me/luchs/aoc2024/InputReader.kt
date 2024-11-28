package me.luchs.aoc2024

fun readInput(day: String): String {
    return object {}.javaClass.getResourceAsStream("/input/$day")?.bufferedReader()?.use {
        it.readText()
    } ?: throw IllegalArgumentException("Can not read file $day")
}