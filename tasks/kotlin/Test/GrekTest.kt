import org.testng.Assert
import org.testng.Reporter
import org.testng.annotations.Test

internal class GrekTest()
{
    val testDir : String = System.getProperty("user.dir") + "\\Resources";
    val testFilePath : String = System.getProperty("user.dir") + "\\Resources\\file1.txt";

    val properLinesNoKeys = arrayOf("G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one one",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one two",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one three",
            "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: two one");

    @Test(testName = "No keys test dir",
        groups = ["Dir search"])
    fun NoKeysTestDir()
    {
        var commandLine = "grek one $testDir";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesNoKeys[i])
        }
    }

    val properLinesKeyN = arrayOf("G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 1: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 2: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 5: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 1: one one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 2: one two",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 3: one three",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 5: two one");

    @Test(testName = "Line number test dir",
        groups = ["Dir search"])
    fun TestKeyNDir()
    {
        var commandLine = "grek -n one $testDir";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesKeyN[i])
            //Reporter.log(lines[i], true);
        }

        //Assert.assertTrue(true);
    }

    val properLinesKeyR = arrayOf("G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one two",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: one three",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: two one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: one one one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: one two one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: one two three",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: four two one");

    @Test(testName = "Deep search test dir",
        groups = ["Dir search"])
    fun TestKeyRDir()
    {
        var commandLine = "grek -r one $testDir";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesKeyR[i])
            //Reporter.log(lines[i], true);
        }

        //Assert.assertTrue(true);
    }

    val properLinesKeyNR = arrayOf("G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 1: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 2: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file1.txt: 5: one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 1: one one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 2: one two",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 3: one three",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\file2.txt: 5: two one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: 1: one one one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: 2: one two one",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: 3: one two three",
        "G:\\Univer\\Java\\6Sem\\hsai-java-2021\\tasks\\kotlin\\Resources\\InnerDir\\file3.txt: 4: four two one");

    @Test(testName = "Deep search and line number test dir",
        groups = ["Dir search"])
    fun TestKeyNRDir()
    {
        var commandLine = "grek -nr one $testDir";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesKeyNR[i])
            //Reporter.log(lines[i], true);
        }

        //Assert.assertTrue(true);
    }

    val properLinesNoKeysFile = arrayOf("one", "one", "one");

    @Test(testName = "No keys test file",
        groups = ["File search"])
    fun NoKeysTestFile()
    {
        var commandLine = "grek one $testFilePath";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesNoKeysFile[i]);
            //Reporter.log(lines[i], true);
        }

        //Assert.assertTrue(true);
    }

    val properLinesKeyNFile = arrayOf("1: one", "2: one", "5: one");

    @Test(testName = "Line number test file",
        groups = ["File search"])
    fun TestKeyNFile()
    {
        var commandLine = "grek -n one $testFilePath";
        Reporter.log(commandLine, true);
        var grek : Grek = Grek(commandLine);

        var lines = grek.Find();

        for(i in 0..lines.size - 1)
        {
            Assert.assertEquals(lines[i], properLinesKeyNFile[i])
            //Reporter.log(lines[i], true);
        }

        //Assert.assertTrue(true);
    }
}