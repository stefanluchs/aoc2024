package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayElevenTest {

    val example1 = """
        0 1 10 99 999
    """.trimIndent()

    @Test
    fun partOneTimes1() {
        val result = DayEleven(example1).partOne(iterations = 1)
        assertEquals(7, result)
    }

    val example2 = """
        125 17
    """.trimIndent()

    @Test
    fun partOneTimes22() {
        val result = DayEleven(example2).partOne(iterations = 6)
        assertEquals(22, result)
    }

    @Test
    fun partOneTimes25() {
        val result = DayEleven(example2).partOne(iterations = 25)
        assertEquals(55312, result)
    }

    @Test
    fun partTwoTimes1() {
        val result = DayEleven(example1).partTwo(iterations = 1)
        assertEquals(7, result)
    }

    @Test
    fun partTwoTimes22() {
        val result = DayEleven(example2).partTwo(iterations = 6)
        assertEquals(22, result)
    }

    @Test
    fun partTwoTimes25() {
        val result = DayEleven(example2).partTwo(iterations = 25)
        assertEquals(55312, result)
    }

}