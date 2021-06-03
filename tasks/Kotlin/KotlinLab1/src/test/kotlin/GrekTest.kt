import org.junit.jupiter.api.Test

internal class GrekTest {
    val resourcePath: String = "src/test/resources/"


    @Test
    fun RunTest() {
        var paramsgreek: Array<String> =
            listOf("greek", "-B", "1", "-A", "5", "-n", """\d*\+\d*=\d*""", "src/test/resources/1").toTypedArray()
        val grekTest: Grek = Grek(paramsgreek)
        grekTest.run()
    }

    @Test
    fun DeepRunTest() {
        var paramsgreek: Array<String> =
            listOf("greek", "-B", "1", "-A", "5", "-nr", """\d*\+\d*=\d*""", "src/test/resources/1").toTypedArray()
        val grekTest: Grek = Grek(paramsgreek)
        grekTest.run()
    }

    @Test
    fun nSingleFileTest() {
        var paramsgreek: Array<String> =
            listOf("greek", "-A", "5", "-n", """\d*\+\d*=\d*""", "src/test/resources/a.txt").toTypedArray()
        val grekTest: Grek = Grek(paramsgreek)
        grekTest.run()
    }
}