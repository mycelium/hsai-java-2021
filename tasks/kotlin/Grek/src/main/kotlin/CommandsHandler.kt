import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default
import java.io.File
import java.lang.IllegalArgumentException
import java.nio.file.Paths

class CommandsHandler(parser: ArgParser) {
    private val n by parser.flagging("-n", help = "флаг номера строки")
    private val r by parser.flagging("-r", help = "флаг рекурсии")
    private val A by parser.storing("-A",  help = "число строк перед найденным")
        { toInt() }.default(0)
    private val B by parser.storing("-B",  help = "число строк после найденного")
        { toInt() }.default(0)
    private val regex by parser.positional("REGES", help = "регулярка, по которой нужно искать")
        { Regex(this) }
    private val file by parser.positional( "FILE", help = "файл/директория для поиска")
        { File(this) }.default(File(Paths.get("").toAbsolutePath().toString()))

    private val workingDirectory : File = File(Paths.get("").toAbsolutePath().toString())

    fun hand(): List<List<String>> {
        if (!r && file.isDirectory) {
            throw IllegalArgumentException("This is directory")
        }
        return getFiles(file)
            .map {
                FileStringsHandler(
                Context(it, regex, n, file.isDirectory, A, B, "./" + it.relativeTo(workingDirectory).path)
                ).run()
            }
    }

    private fun getFiles(file : File): List<File> {
        return file
            .walk()
            .toList()
            .filter { x -> x.isFile }
    }
}