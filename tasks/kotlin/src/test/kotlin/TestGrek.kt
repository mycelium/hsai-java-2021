import org.junit.Test

class TestGrek {
    var email: String = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}"
    var doc: String = "^[A-Za-z0-9]+$"
    var folderPath: String = "./testData/"
    var filePath1: String = folderPath + "testData1.txt"
    var filePath2: String = "testData2.txt"

    @Test
    fun testFile1() {
        val t1 = Grek("grek -n $email $filePath1")
        t1.execute()
    }

    @Test
    fun testFile2() {
        val t1 = Grek("grek -n $doc $filePath1")
        t1.execute()
    }

    @Test
    fun testFolder() {
        val t1 = Grek("grek -nr $doc $folderPath")
        t1.execute()
    }

    @Test
    fun testFile3(){
        val t1 = Grek("grek -n -A 2 -B 2 $doc $filePath1")
        t1.execute()
    }
}