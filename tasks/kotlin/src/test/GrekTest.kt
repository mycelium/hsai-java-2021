import main.*
import org.junit.*

class GrekTest {

    @Test
    fun test1() {
        val request = "grek -n [a-zA-Z]{10} src\\test\\resources\\test.txt"
        val expected="6:categories\n" +
                "9:Barbecutie\n"
        Assert.assertEquals(expected, initiateGrek(request))
    }

    @Test
    fun test2() {
        val request = "grek -nr ^[a-zA-Z]*[0-9]+\$ src\\test\\resources"
        val expected="samples\\test.txt:15:asdADGADS213\n" +
                "samples\\test2.txt:10:gsdFDHD424\n"
        Assert.assertEquals(expected, initiateGrek(request))
    }

    @Test
    fun test3() {
        val request = "grek -n -A 1 -B 1 oak src\\test\\resources\\test.txt"
        val expected="3-cats\n" +
                "4:oak\n" +
                "5-many cats\n" +
                "--\n"
        Assert.assertEquals(expected, initiateGrek(request))
    }

    @Test
    fun test4() {
        val request = "grek -nr -A 2 -B 2 categories rc\\test\\resources"
        val expected="samples\\test.txt:4-oak\n" +
                "samples\\test.txt:5-many cats\n" +
                "samples\\test.txt:6:categories\n" +
                "samples\\test.txt:7-cut ropes\n" +
                "samples\\test.txt:8-cute\n" +
                "--\n"
        Assert.assertEquals(expected, initiateGrek(request))
    }
}