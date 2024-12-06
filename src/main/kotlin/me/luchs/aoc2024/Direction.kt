package me.luchs.aoc2024

enum class Direction(val row: Int, val column: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1);

    fun turnRight(): Direction = when(this) {
        UP -> RIGHT
        DOWN -> LEFT
        LEFT -> UP
        RIGHT -> DOWN
        UP_LEFT -> TODO()
        UP_RIGHT -> TODO()
        DOWN_LEFT -> TODO()
        DOWN_RIGHT -> TODO()
    }
}
