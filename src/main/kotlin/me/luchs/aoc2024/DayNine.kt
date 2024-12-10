package me.luchs.aoc2024

data class DayNine(val input: String) {

    val data = input.map { it.digitToInt() }.toIntArray()

    fun partOne(): Long {

        var size = 0
        for ((_, length) in data.withIndex()) {
            size += length
        }

        val array = IntArray(size) { -1 }
        var pos = 0
        for ((index, length) in data.withIndex()) {
            if (index % 2 == 0) {
                val id = index / 2
                for (j in 0 until length) {
                    array[pos + j] = id
                }
            }
            pos += length
        }

        var head = 0
        var tail = array.size - 1
        while (true) {
            while (array[head] >= 0) {
                head++
            }
            while (array[tail] == -1) {
                tail--
            }
            if (head >= tail) break
            array[head] = array[tail]
            array[tail] = -1
            head++
            tail--
        }

        var sum = 0L
        for (i in array.indices) {
            val id = array[i]
            if (id < 0) continue
            sum += id.toLong() * i.toLong()
        }
        return sum
    }

}
