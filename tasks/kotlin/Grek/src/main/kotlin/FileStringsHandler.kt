import java.io.File
import java.nio.charset.Charset

class FileStringsHandler(val contxt: Context) {
    private val listOfLines: List<String> = getLines(contxt.file)
    private val listOfIndexes : List<Int> = findIndexes(contxt.regex, contxt.A, contxt.B)
    private var listResult: List<String> = filter()

    private fun getLines(file: File): List<String> {
        return file
            .readLines(Charset.defaultCharset())
    }

    private fun findIndexes(regex: Regex, A: Int, B: Int): List<Int> {
        val filtred = listOfLines
            .mapIndexed { index, s -> Pair(index, s) }
            .filter { s -> regex.containsMatchIn(s.second) }
            .map { pair -> pair.first + 1 }

        val res = mutableListOf<Int>()
        for (i in filtred) {
            for (j in i-A..i+B) {
                if (j >= 1 && j <= listOfLines.size) {
                    res.add(j)
                }

            }
        }
        return res.distinct()
    }

    private fun filter(): List<String> {
        return listOfLines
            .filterIndexed { index, _ -> listOfIndexes.contains(index + 1) }
    }

    private fun concatNumber(): List<String> {
        return listResult
            .mapIndexed { index, s -> "${listOfIndexes[index]}:${s}" }
    }

    private fun concatName(fileName: String): List<String> {
        return listResult
            .map { str -> "${fileName}:${str}" }
    }

    fun run(): List<String> {
        if (contxt.n) {
            listResult = concatNumber()
        }
        if (contxt.r) {
            listResult = concatName("./" + contxt.file.relativeTo(contxt.workingDir).path)
        }
        return listResult
    }
}