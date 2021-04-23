package grek

import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import org.testng.asserts.*
import java.io.*
import kotlin.test.assertEquals
import java.nio.charset.StandardCharsets.UTF_8


class IntegrationTest {
    private val expectedDir = "src/test/resources/grek/expected/"
    private val n = 4
    private val charSet = UTF_8

    @DataProvider
    public fun dataProvider(): Array<Array<Serializable>> {
        return arrayOf(
            arrayOf("test1", arrayOf(
                "-n", "шл", "src/test/resources/grek/text1.txt")),
            arrayOf("test2", arrayOf(
                "-nr", "анн", "src/test/resources/grek/recursion")),
            arrayOf("test3", arrayOf(
                "-A", "3", "-B", "4", "-n", "авангард", "src/test/resources/grek/text1.txt")),
            arrayOf("test4", arrayOf(
                "-B", "-A", "-n"
            ))
        )
    }

    @Test(dataProvider = "dataProvider")
    public fun integrationTest(name: String, args: Array<String>) {
        val expectedText = FileInputStream("$expectedDir$name.txt").use {
            it.reader(charSet).readText()
        }
        val ba = ByteArrayOutputStream()
        val stream = PrintStream(ba)
        val gk = Grek(stream)
        gk.run(args)
        assertEquals(expectedText, ba.toString())
    }

}