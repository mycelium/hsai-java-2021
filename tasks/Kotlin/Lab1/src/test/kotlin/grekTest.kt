
import org.junit.*
import java.io.File

class GrekTest {

    @Test
    fun test() {
        val request : Array<String> = arrayOf("-nr", "^[a-zA-Z]*[0-9]+\$", "src\\test\\resources")
        val expected=
            File("").absolutePath + "\\src\\test\\resources\\test.txt\n" +
            File("").absolutePath + "\\src\\test\\resources\\test2.txt\n" +
            "1: asXFAWSE23\n\n" +
            File("").absolutePath + "\\src\\test\\resources\\test3.txt\n" +
            "1: asd12312\n\n"
        main(request)
        print("\nExpected:\n$expected")
    }

    @Test
    fun test2() {
        val request : Array<String> = arrayOf("-n", "-A", "2", "-B", "2", "Юродствуй, воруй, молись!", "src\\test\\resources\\test.txt")
        val expected=
            "22: И каждому свой гроб.\n" +
            "23: \n" +
            "24: Юродствуй, воруй, молись!\n" +
            "25: Будь одинок, как перст!..\n" +
            "26: ...Словно быкам – хлыст,\n\n"
        main(request)
        print("\nExpected:\n$expected")
    }

    @Test
    fun test3() {
        val request : Array<String> = arrayOf("-nr", "-A", "3", "Ибо вечность – Богам.", "src\\test\\resources")
        val expected=
            File("").absolutePath + "\\src\\test\\resources\\test.txt\n" +
            "9: Ибо вечность – Богам.\n" +
            "10: Бренность – удел быков...\n" +
            "11: Богово станет нам\n" +
            "12: сумерками Богов.\n\n" +
            File("").absolutePath + "\\src\\test\\resources\\test2.txt\n" +
            File("").absolutePath + "\\src\\test\\resources\\test3.txt\n"

        main(request)
        print("\nExpected:\n$expected")
    }
}