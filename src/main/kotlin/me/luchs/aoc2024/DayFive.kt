package me.luchs.aoc2024

fun String.toCondition(): (String) -> Boolean {
    val (left, right) = this.split('|')
    return { s: String ->
        if (s.contains(left) && s.contains(right))
            s.indexOf(left) < s.indexOf(right) else true
    }
}

data class DayFive(val input: String) {

    fun partOne(): Int {
        val (rules, pages) = input.split("\n\n")
        val conditions = rules.split("\n").map { it.toCondition() }
        return pages.split("\n")
            .asSequence()
            .filter { page -> conditions.all { it.invoke(page) } }
            .map { it.split(',') }
            .map { it[it.size / 2] }
            .map { it.toInt() }
            .sum()
    }

    fun partTwo(): Int {
        val (rules, pages) = input.split("\n\n")
        val conditions = rules.split("\n").map { it.toCondition() }
        val comparator = rules.split("\n").toComparator()
        val invalid = pages.split("\n")
            .filterNot { page -> conditions.all { it.invoke(page) } }
            .map { it.split(',') }
        val sorted = invalid.map { it.sortedWith(comparator) }
        return sorted.map { it[it.size / 2] }.map { it.toInt() }.sum()
    }

    private fun List<String>.toComparator(): Comparator<String> {
        val rules = this.map { it.split('|') }
        return Comparator { s1, s2 ->
            for ((left, right) in rules) {
                when {
                    s1 == left && s2 == right -> return@Comparator -1
                    s1 == right && s2 == left -> return@Comparator 1
                }
            }
            s1.compareTo(s2)
        }
    }

}
