package me.luchs.aoc2024

data class DayEleven(val input: String) {

    private val stones = input.trim().split(" ").map { it.toLong() }

    private val Long.digitCount: Int
        get() = this.toString().length

    fun partOne(iterations: Int = 25): Long = solve(iterations)

    fun partTwo(iterations: Int = 75): Long = solve(iterations)

    private fun solve(iterations: Int): Long {
        // use dynamic programming with cache for computed solutions
        val cache = mutableMapOf<Pair<Long, Int>, Long>()
        return stones.sumOf { computeSize(it, iterations, cache) }
    }

    private fun computeSize(stone: Long, iterations: Int, cache: MutableMap<Pair<Long, Int>, Long>): Long {

        // no iterations left -> return count 1
        if (iterations == 0) {
            return 1L
        }

        val key = stone to iterations

        // check if solution is already in cache
        val cachedValue = cache[key]
        if (cachedValue != null) {
            return cachedValue
        } else { // compute solution
            return if (0L == stone) {
                computeSize(1L, iterations - 1, cache)
            } else if (stone.digitCount % 2 == 0) {
                val s = stone.toString()
                val left = s.substring(0 until s.length / 2).toLong()
                val right = s.substring(s.length / 2 until s.length).toLong()
                computeSize(left, iterations - 1, cache) + computeSize(right, iterations - 1, cache)
            } else {
                computeSize(stone * 2024L, iterations - 1, cache)
            }.also { // add solution to cache
                cache[key] = it
            }
        }
    }

}
