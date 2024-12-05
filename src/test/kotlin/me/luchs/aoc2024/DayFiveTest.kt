package me.luchs.aoc2024

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DayFiveTest {

    val example = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun partOne() {
        val result = DayFive(example).partOne()
        assertEquals(143, result)
    }

    @Test
    fun partTwo() {
        val result = DayFive(example).partTwo()
        assertEquals(123, result)
    }

    @Test
    fun condition() {
        val condition: (String) -> Boolean = "47|53".toCondition()
        val result = condition.invoke("75,47,61,53,29")
        assertTrue(result)
    }

    @Test
    fun condition2() {
        val condition: (String) -> Boolean = "97|13".toCondition()
        val result = condition.invoke("75,47,61,53,29")
        assertTrue(result)
    }

}