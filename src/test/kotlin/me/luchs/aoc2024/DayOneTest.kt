package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayOneTest {

    val example = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayOne(example).partOne()
        assertEquals(11, result)
    }

    @Test
    fun partTwo() {
        val result = DayOne(example).partTwo()
        assertEquals(31, result)
    }

}