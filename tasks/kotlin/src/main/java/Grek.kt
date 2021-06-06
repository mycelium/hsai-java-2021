import java.io.File
import java.lang.IllegalArgumentException

val template = "^grek\\s(-A\\s\\d+\\s)?(-B\\s\\d+\\s)?-n(r)?\\s.+\\s.+$".toRegex()

class Grek {
    private var r = false
    private var A = 0
    private var B = 0
    private var regEx = Regex("")
    private var path = String()

    fun getMatch(request: String): String{
        if(!template.matches(request))
            throw IllegalArgumentException("Команда не соответствует шаблону")
        val params=request.split(" ")
        for(i in params.indices)
            when(params[i]){
                "-nr" -> r=true
                "-A" -> A=params[i+1].toInt()
                "-B" -> B=params[i+1].toInt()
            }
        regEx=params[params.lastIndex-1].toRegex()
        path=params.last()
         if (File(path).exists())
            if (r)
                return findMatchInFolder(regEx,path,A,B)
            else if(File(path).isFile)
                    return findMatchInFile(regEx,path,A,B)
                 else throw IllegalArgumentException("$path является директорией")
         else throw IllegalArgumentException("$path не существует")
    }

    fun findMatchInFile(regEx: Regex, pathToSingleFile: String, A: Int = 0, B: Int = 0): String {
        var result = "  matches in $pathToSingleFile \n"
        val file = File(pathToSingleFile)
        val text = file.readText()
        val matches = regEx.findAll(text)
        for (match in matches) {
            val number: Int = lineNumber(file, match.range.first)
            val lines = file.readLines()
            for (i in number - A..number + B) {
                if (i >= 0 && i <= lines.size - 1)
                    result += "$i: ${lines[i]} \n"
            }
            result += "\n"
        }

        return result
    }

    fun findMatchInFolder(regEx: Regex, pathToFolder: String, A: Int, B: Int): String {
        var result = String()
        for (file in File(pathToFolder).listFiles()) {
            if (file.isDirectory)
                result += findMatchInFolder(regEx, file.absolutePath, A, B)
            else
                result += findMatchInFile(regEx, file.absolutePath, A, B)
        }
        return result
    }

    fun lineNumber(file: File, index: Int): Int {
        val lines = file.readLines()
        var currentIndex: Int = -1
        var lineNum: Int = -1

        do {
            lineNum++
            currentIndex += lines[lineNum].length + 2
        } while ((index > currentIndex) && (lineNum < lines.size))

        return lineNum
    }
}