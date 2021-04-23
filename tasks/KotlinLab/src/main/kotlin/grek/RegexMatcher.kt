package grek

/**
 * Regex wrapper.
 */
class RegexMatcher constructor(pattern: String) {
    private val regex: Regex = Regex(pattern)

    /**
     * Можно тут прикрутить параллелизм????
     */
    fun findMatches(strings: List<String>): List<Int> = strings.mapIndexed { i: Int, s:String ->
        if (regex.containsMatchIn(s)) i else -1 }.filter { i -> i >= 0 }

}