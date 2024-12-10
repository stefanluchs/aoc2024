package me.luchs.aoc2024

data class DayTen(val input: String) {

    private val map = input.toCharMatrix()

    fun partOne(): Int {

        val trailheads = map.entries('0').toList()

        val neighbours = { map: CharMatrix, current: Coordinate2D ->
            val currentValue = map.valueOf(current)!!.digitToInt()
            current.adjacentFour()
                .map { it to map.valueOf(it) }
                .filter { it.second?.digitToInt() == currentValue + 1 }
                .map { it.first }
        }

        val isResult = { map: CharMatrix, current: Coordinate2D -> map.valueOf(current)?.digitToInt() == 9 }

        return trailheads.map { map.bfs(it, neighbours, isResult) }.sumOf{ it.size }
    }

    fun CharMatrix.bfs(
        start: Coordinate2D,
        next: (CharMatrix, Coordinate2D) -> List<Coordinate2D>,
        isResult: (CharMatrix, Coordinate2D) -> Boolean
    ): List<Coordinate2D> {
        val visited = mutableSetOf<Coordinate2D>()
        val queue = ArrayDeque<Coordinate2D>()
        val result = mutableListOf<Coordinate2D>()

        queue.add(start)

        while (queue.isNotEmpty()) {

            val current = queue.removeFirst()

            visited.add(start)

            if (isResult.invoke(map, current)) {
                result.add(current)
            }

            next.invoke(this, current)
                .filterNot { it in visited }
                .filterNot { it in queue }
                .let { queue.addAll(it) }
        }

        return result
    }


}
