package grek

import grek.CommandInterpreter

import org.testng.annotations.*
import org.testng.asserts.*
import kotlin.test.assertEquals

class CommandInterpreterTest {
    @DataProvider
    public fun dataProvider(): Array<Array<Any>> {
        return arrayOf(
            arrayOf(listOf("-n", "regex", "path"),
                true, "regex", "path", true, 0, 0),
            arrayOf(listOf("-nr", "regex", "path"),
                true, "regex", "path", false, 0, 0),
            arrayOf(listOf("-B", "1", "-n", "regex", "path"),
                true, "regex", "path", true, 0, 1),
            arrayOf(listOf("-B", "1", "-nr", "regex", "path"),
                true, "regex", "path", false, 0, 1),
            arrayOf(listOf("-A", "1", "-n", "regex", "path"),
                true, "regex", "path", true, 1, 0),
            arrayOf(listOf("-A", "1", "-nr", "regex", "path"),
                true, "regex", "path", false, 1, 0),
            arrayOf(listOf("-A", "1", "-B", "2", "-n", "regex", "path"),
                true, "regex", "path", true, 1, 2),
            arrayOf(listOf("-A", "1", "-B", "2", "-nr", "regex", "path"),
                true, "regex", "path", false, 1, 2),
            arrayOf(listOf("-B", "1", "-A", "2", "-n", "regex", "path"),
                true, "regex", "path", true, 2, 1),
            arrayOf(listOf("-B", "1", "-A", "2", "-nr", "regex", "path"),
                true, "regex", "path", false, 2, 1),
            arrayOf(listOf("-A", "1", "-A", "2", "-n", "regex", "path"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-B", "1", "-B", "2", "-n", "regex", "path"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-A", "0", "-n", "regex", "path"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-B", "-2", "-A", "2", "-n", "regex", "path"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-unknown", "1", "-A", "2", "-n", "regex", "path"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-n", "regex"),
                false, "", "", false, 0, 0),
            arrayOf(listOf("-n", "regex", "path", "-A", "4"),
                false, "", "", false, 0, 0)
            )
    }

    @Test(dataProvider = "dataProvider")
    public fun testTranslation(input: List<String>,
                        correct: Boolean,
                        regex: String,
                        path: String,
                        isSingle: Boolean,
                        a: Int,
                        b: Int
    ) {
        val ci = CommandInterpreter(input)
        //if (correct != ci.correct) println(input + " - " + ci.log)
        assertEquals(correct, ci.correct)
        if (correct) {
            val softAssert = SoftAssert()
            softAssert.assertEquals(regex, ci.parRegex)
            softAssert.assertEquals(path, ci.parPath)
            softAssert.assertEquals(isSingle, ci.parSingleFile)
            softAssert.assertEquals(a, ci.parA)
            softAssert.assertEquals(b, ci.parB)
            softAssert.assertAll()
        }
    }
}