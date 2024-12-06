package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DaySixTest {

    val example = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DaySix(example).partOne()
        assertEquals(41, result)
    }

    @Test
    fun partTwo() {
        val result = DaySix(example).partTwo()
        assertEquals(6, result)
    }

}