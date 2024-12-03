package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayTwoTest {

    val example = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayTwo(example).partOne()
        assertEquals(2, result)
    }

    @Test
    fun partTwo() {
        val result = DayTwo(example).partTwo()
        assertEquals(4, result)
    }

}