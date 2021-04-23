package grek

import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import org.testng.asserts.*
import kotlin.test.assertEquals

class FileTest {
    @DataProvider
    public fun existsProvider(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("src/test/resources/grek/text1.txt", true),
            arrayOf("src/test/resources/grek/text2.txt", true),
            arrayOf("src/test/resources/grek/text3.txt", true),
            arrayOf("src/test/resources/grek/text4.txt", true),
            arrayOf("src/test/resources/grek/text5.txt", false)
            )
    }

    @DataProvider
    public fun readingProvider(): Array<Array<Any>> {
        return arrayOf(
            arrayOf("src/test/resources/grek/text1.txt",
                listOf(3, 2),
                listOf(listOf("\"Сегодня мы исполним грусть его\" --", "Так, верно, встречи обо мне сказали.",
                        "Таков был лавок сумрак. Таково"),
                        listOf("Окно с мечтой смятенною азалий.", ""))),
            arrayOf("src/test/resources/grek/text2.txt",
                listOf(3, 0, 2),
                listOf(listOf("Lorem ipsum dolor sit amet, consectetur adipiscing elit,",
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
                    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi"),
                emptyList(), listOf("ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit",
                    "in voluptate velit esse cillum dolore eu fugiat nulla pariatur.")))
            )
    }

    @Test(dataProvider = "existsProvider")
    public fun existsTest(path: String, exists: Boolean) {
        val fileHandler = FileHandler(path)
        assertEquals(fileHandler.exists(), exists)
    }

    @Test(dataProvider = "readingProvider")
    public fun readingTest(path: String, sizes: List<Int>, lines: List<List<Int>>) {
        val fileHandler = FileHandler(path)
        val softAssert = SoftAssert()
        for (i in sizes.indices) {
            val read = fileHandler.readNext(sizes[i])
            softAssert.assertEquals(read, lines[i])
        }
        softAssert.assertAll()
    }
}