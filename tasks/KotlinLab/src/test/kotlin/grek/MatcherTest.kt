package grek

import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import org.testng.asserts.*

import java.io.File
import kotlin.test.assertEquals

class MatcherTest {
    @DataProvider
    fun dataProvider(): Array<Array<Any>> {
        val input = File("src/test/resources/grek/text1.txt")
        val lines = input.readLines()
        return arrayOf(
            arrayOf(lines, "дома", listOf(6, 16)),
            arrayOf(lines, "ого", listOf(6, 8)),
            arrayOf(lines, "Пастернак", emptyList<Int>()),
            arrayOf(lines, "--", listOf(0, 7)))
    }

    @Test(dataProvider = "dataProvider")
    fun testMatcher(input: List<String>,
                    string: String,
                    indexes: List<Int>) {
        val matcher = RegexMatcher(string)
        assertEquals(indexes, matcher.findMatches(input))
    }
}