package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DaySevenTest {

    val example = """
        190: 10 19
        3267: 81 40 27
        83: 17 5
        156: 15 6
        7290: 6 8 6 15
        161011: 16 10 13
        192: 17 8 14
        21037: 9 7 18 13
        292: 11 6 16 20
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DaySeven(example).partOne()
        assertEquals(3749, result)
    }

    @Test
    fun partTwo() {
        val result = DaySeven(example).partTwo()
        assertEquals(11387, result)
    }

}