import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

internal class GrekKtTest {

    private fun getNumOfMatches(result: String) : Int {
        return result.split("---").dropLast(1).size
    }

    private fun getNumOfFilesTraversed(result: String) : Int {
        return result.split("===").dropLast(1).size
    }

    @Test
    fun testSingleFileMultipleMatches() {
        val result = matchFile("./src/test/resources/resources.txt", "[A-Z][a-z]{5}".toRegex())
        assertEquals(3, getNumOfMatches(result))
    }

    @Test
    fun testSingleFileSingleMatch() {
        val result = matchFile("./src/test/resources/resources.txt",
            "[A-Z][a-z]{5} [A-Z][a-z]{5} [A-Z][a-z]{5}".toRegex())
        assertEquals(1, getNumOfMatches(result))
    }

    @Test
    fun testSingleFileNoMatches() {
        val result = matchFile("./src/test/resources/resources.txt",
            "\\w{23}".toRegex())
        assertEquals(0, getNumOfMatches(result))
    }

    @Test
    fun testTraverseAllFilesInResources() {
        val result = matchFolder("./src/test/resources/", "[A-Z][a-z]{5}".toRegex())
        assertEquals(4, getNumOfFilesTraversed(result))
    }

    @Test
    fun testTraverseAllFilesInTestFolder() {
        val result = matchFolder("./src/test/resources/test_folder", "[A-Z][a-z]{5}".toRegex())
        assertEquals(3, getNumOfFilesTraversed(result))
    }

    @Test
    fun matchFolderSingleMatch() {
        val result = matchFolder("./src/test/resources/", "Helloo Helloo Helloo".toRegex())
        assertEquals(1, getNumOfMatches(result))
    }

    @Test
    fun matchFolderNoMatches() {
        val result = matchFolder("./src/test/resources/", "Helloo1 Helloo1 Helloo1".toRegex())
        assertEquals(0, getNumOfMatches(result))
    }

    @Test
    fun matchFolderMultipleMatch() {
        val result = matchFolder("./src/test/resources/test_folder/test/", "posit[a-zA-Z]{5}".toRegex())
        assertEquals(4, getNumOfMatches(result))
    }

    @Test
    fun containsBeforeAndAfter() {
        val result = matchFile("./src/test/resources/test_folder/test_folder.txt",
            "crashlytics.properties".toRegex(), before = 2, after = 2)
        assertTrue(result.contains("# Crashlytics plugin (for Android Studio and IntelliJ)"))
        assertTrue(result.contains("com_crashlytics_export_strings.xml"))
        assertTrue(result.contains("crashlytics.properties"))
        assertTrue(result.contains("crashlytics-build.properties"))
        assertTrue(result.contains("fabric.properties"))
    }

    @Test
    fun containsNumbering() {
        val result = matchFile("./src/test/resources/test_folder/test_folder.txt",
            "crashlytics.properties".toRegex(), before = 0, after = 2, numbered = true)
        assertTrue(result.contains("38"))
        assertTrue(result.contains("39"))
        assertTrue(result.contains("40"))
    }

}