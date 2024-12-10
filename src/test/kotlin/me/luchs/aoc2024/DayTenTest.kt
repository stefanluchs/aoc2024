package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayTenTest {

    val example = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
    """.trimIndent()


    @Test
    fun partOne() {
        val result = DayTen(example).partOne()
        assertEquals(36, result)
    }

}