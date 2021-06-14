import org.junit.Test
import wsyconan.kotlin.grek.main

class TestGrek {
    var email: String = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
    var doc: String = "^[A-Za-z0-9]+$"
    var folderPath: String = "./testData/"
    var filePath1: String = folderPath + "testData1.txt"
    var filePath2: String = "testData2.txt"

    @Test
    fun testFile1() {
        val tester = main("grek -n $email $filePath1")
    }

    @Test
    fun testFile2() {
        val tester = main("grek -n $doc $filePath1")
    }

    @Test
    fun testFolder() {
        val tester = main("grek -nr $doc $folderPath")
    }

    @Test
    fun testFile3(){
        val tester = main("grek -n -A 2 -B 2 $doc $filePath1")
    }

    @Test
    fun testFold2(){
        val tester = main("grek -QaQ")
    }

    @Test
    fun testFold3(){
        val tester = main("grek -nr $email ${folderPath+1}")
    }
}