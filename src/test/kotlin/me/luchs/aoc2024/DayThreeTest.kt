package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayThreeTest {

    val example = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayThree(example).partOne()
        assertEquals(161, result)
    }

    val example2 = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent()

    @Test
    fun partTwo() {
        val result = DayThree(example2).partTwo()
        assertEquals(48, result)
    }

}