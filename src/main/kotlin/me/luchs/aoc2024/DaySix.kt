package me.luchs.aoc2024

data class DaySix(val input: String) {

    private val matrix = input.toCharMatrix()
    private val dimensions = matrix.dimensions()

    fun partOne(): Int {
        val start = matrix.entry('^')
        val path: MutableList<Pair<Pair<Int, Int>, Direction>> = matrix.pathFrom(start)
        return path.map { it.first }.distinct().size
    }

    fun partTwo(): Int {
        val start = matrix.entry('^')
        val path = matrix.pathFrom(start).map { it.first }.distinct()
        // naive brute force solution
        return path.drop(1) // skip first
            .parallelStream() // parallel execution on all cpu cores ;-)
            .filter { obstruction -> matrix.pathContainsCycle(start, obstruction) }
            .toArray().size
    }

    private fun CharMatrix.pathFrom(start: Pair<Int, Int>): MutableList<Pair<Pair<Int, Int>, Direction>> {
        var current = start to Direction.UP
        val positions: MutableList<Pair<Pair<Int, Int>, Direction>> = mutableListOf()

        while (current !in positions) {
            // stop if position is outside the 2D matrix
            if (current.first.first !in dimensions.first || current.first.second !in dimensions.second) break

            // add current position to history
            positions.add(current)

            // determine the next postion and its value
            val (position, direction) = current
            val next: Pair<Int, Int> = position.first + direction.row to position.second + direction.column
            val nextValue = this.valueOf(next)

            // turn right if the next position is blocken ('#')
            if ('#' == nextValue) {
                val nextDirection = direction.turnRight()
                current = position to nextDirection
            } else {
                current = next to direction
            }
        }
        return positions
    }

    private fun CharMatrix.pathContainsCycle(start: Pair<Int, Int>, obstruction: Pair<Int, Int>): Boolean {
        var current = start to Direction.UP
        val positions: MutableList<Pair<Pair<Int, Int>, Direction>> = mutableListOf()

        while (current !in positions) {
            // return false if position is outside the 2D matrix
            if (current.first.first !in dimensions.first || current.first.second !in dimensions.second) return false

            if (current in positions) {
                return true
            }

            // add current position to history
            positions.add(current)

            // determine the next postion and its value
            val (position, direction) = current
            val next: Pair<Int, Int> = position.first + direction.row to position.second + direction.column
            val nextValue = this.valueOf(next)

            // turn right if the next position is blocken ('#')
            if (obstruction == next) {
                val nextDirection = direction.turnRight()
                current = position to nextDirection
            } else if ('#' == nextValue) {
                val nextDirection = direction.turnRight()
                current = position to nextDirection
            } else {
                current = next to direction
            }
        }

        return true
    }

}
