package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayFourTest {

    val example = """
            MMMSXXMASM
            MSAMXMSMSA
            AMXSXMAAMM
            MSAMASMSMX
            XMASAMXAMM
            XXAMMXXAMA
            SMSMSASXSS
            SAXAMASAAA
            MAMMMXMMMM
            MXMXAXMASX
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayFour(example).partOne()
        assertEquals(18, result)
    }

    @Test
    fun partTwo() {
        val result = DayFour(example).partTwo()
        assertEquals(9, result)
    }

}