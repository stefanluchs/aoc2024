package me.luchs.aoc2024

fun <T> List<T>.combinations() = this.flatMapIndexed { i, a -> this.drop(i + 1).map { b -> Pair(a, b) } }
