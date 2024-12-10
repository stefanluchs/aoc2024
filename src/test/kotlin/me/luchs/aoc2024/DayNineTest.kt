package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayNineTest {

    val example = """
        2333133121414131402
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayNine(example).partOne()
        assertEquals(1928, result)
    }

    @Test
    fun partTwo() {
        val result = DayNine(example).partTwo()
        assertEquals(2858, result)
    }

}