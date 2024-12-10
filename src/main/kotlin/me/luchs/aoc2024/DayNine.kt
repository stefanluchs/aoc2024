package me.luchs.aoc2024

import java.util.*

data class DayNine(val input: String) {

    val data = input.map { it.digitToInt() }.toIntArray()

    fun partOne(): Long = data.toIdArray()
        .fillSpaces()
        .mapIndexed { index, id -> index to id }
        .filter { (_, id) -> id > 0 }
        .sumOf { (index, id) -> index.toLong() * id.toLong() }

    fun partTwo(): Long {
        val (spaces, files) = data.toFilesAndSpaces()

        // iterate over files in reverse order
        for (id in files.size - 1 downTo 0) {
            val (filePosition, fileLength) = files[id]

            // Find first fitting position
            val iterator = spaces.iterator()
            while (iterator.hasNext()) {
                val (spacePosition, spaceLength) = iterator.next()

                // break the loop after the remaining space is behind the current file
                if (spacePosition >= filePosition) break

                // not enough space for file
                if (spaceLength < fileLength) continue

                files[id] = spacePosition to fileLength

                // Remove space
                iterator.remove()

                // add new space if space remains after file was moved in the new position
                if (spaceLength > fileLength) {
                    spaces[spacePosition + fileLength] = spaceLength - fileLength
                }
                break
            }
        }

        return files
            .mapIndexed { id, (position, length) -> (position until position + length).sumOf { id.toLong() * it.toLong() } }
            .sum()
    }

    private fun IntArray.toIdArray(): IntArray {
        val size = this.sum() // sum of size of all blocks (files and spaces)

        val array = IntArray(size) { -1 } // use -1 as identifier for empty space

        var position = 0
        // fill array with file ids at the respective locations
        for ((index, length) in this.withIndex()) {
            if (index % 2 == 0) { // files always have even index
                val id = index / 2
                for (j in 0 until length) {
                    array[position + j] = id
                }
            }
            position += length // shift pointer
        }

        return array
    }

    private fun IntArray.fillSpaces(): IntArray {
        // move file contents from back of the array to the free (-1) spots in the array
        var head = 0
        var tail = this.size - 1
        while (true) {
            // move head to the next free spot in the array
            while (this[head] >= 0) {
                head++
            }
            // move tail to the next occupied spot at the back
            while (this[tail] == -1) {
                tail--
            }
            if (head >= tail) break
            this[head] = this[tail]
            this[tail] = -1
            head++
            tail--
        }

        return this
    }

    private fun IntArray.toFilesAndSpaces(): Pair<TreeMap<Int, Int>, MutableList<Pair<Int, Int>>> {
        val spaces = TreeMap<Int, Int>()
        val files = mutableListOf<Pair<Int, Int>>()

        var position = 0
        // extract files and spaces
        for ((index, length) in this.withIndex()) {
            if (index % 2 == 0) { // files always have even id
                files.add(position to length)
            } else {
                spaces[position] = length
            }
            position += length
        }

        return spaces to files
    }

}
