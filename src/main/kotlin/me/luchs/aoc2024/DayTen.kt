package me.luchs.aoc2024

data class DayTen(val input: String) {

    private val map = input.toCharMatrix()

    fun partOne(): Int {

        val isResult = { map: CharMatrix, current: Coordinate2D, _: List<Coordinate2D> ->
            if (isTarget(map.valueOf(current))) {
                current
            } else {
                null
            }
        }

        return map.entries('0').map { map.bfs(start = it, neighbours, isResult) }.sumOf { it.size }
    }

    fun partTwo(): Int {
        return map.entries('0')
            .map { map.allPathsBetween(start = it, neighbours = neighbours, isTarget = isTarget) }
            .sumOf { it.size }
    }

    val isTarget = { value: Char? -> value?.digitToInt() == 9 }

    private val neighbours = { map: CharMatrix, current: Coordinate2D ->
        val currentValue = map.valueOf(current)!!.digitToInt()
        current.adjacentFour()
            .map { it to map.valueOf(it) }
            .filter { it.second?.digitToInt() == currentValue + 1 }
            .map { it.first }
    }

}
