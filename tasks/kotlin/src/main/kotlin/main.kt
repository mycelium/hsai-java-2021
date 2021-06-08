import java.io.File
import java.util.regex.PatternSyntaxException

data class Parameters(
    var regex: String = "",
    var path: String = "",
    var nParameter: Boolean = false,
    var rParameter: Boolean = false,
    var AParameter: Boolean = false,
    var BParameter: Boolean = false,
    var aNumber: Int = 0,
    var bNumber: Int = 0
)
fun init(input: String){
    var param= Parameters();
    val strings: List<String> = input.split(' ')
    if (strings[0] == "grek" && strings.size >= 4){
        for (string in strings.withIndex()) {
            when (string.value) {
                "-n" -> param.nParameter = true
                "-nr" -> param.rParameter = true
                "-B" -> {
                    param.BParameter = true
                    param.bNumber = input[string.index + 1].toInt()
                }
                "-A" -> {
                    param.AParameter = true
                    param.aNumber = input[string.index + 1].toInt()
                }
            }
        }
        param.path = strings[strings.lastIndex]
        param.regex = strings[strings.lastIndex - 1]
    }
    else println("Incorrect input")
    runGrek(param)
}
fun runGrek(param:Parameters){
    try {
        Regex( param.regex)
    }catch(ex: PatternSyntaxException){
        println("Regex is wrong")
        return
    }
    if(File( param.path).isFile || File( param.path).isDirectory) {
        if ( param.nParameter && File( param.path).isFile)
            search( param.bNumber, param.aNumber,  param.regex, File( param.path), false)
        if ( param.rParameter && File( param.path).isDirectory)
            recursiveSearch( param.regex, File( param.path),  param.bNumber,  param.aNumber)
    }
    else println("Path is wrong")
}
fun findFiles(files: MutableList<File>, directory: File){
    for (item in directory.listFiles()) {
        if (item.isDirectory) {
            findFiles(files, item)
        }
        else {
            files.add(item)
        }
    }
}
fun recursiveSearch(regex: String, path: File, b: Int, a: Int){
    val files: MutableList<File> = mutableListOf()
    findFiles(files, path)
    for (file in files){
        search(b, a, regex, file, true)
    }
}

fun search(b: Int, a: Int, regex: String, file: File, recursive:Boolean) {
    if(a>0 && b>0) {
        val strings: List<String> = file.readLines()

        for ((count, line) in strings.withIndex()) {
            if (line.contains(Regex(regex))) {
                for (i in maxOf(count - b, 0)..minOf(count + a, strings.size-1)) {
                    if (recursive) {
                        if (i == count) {
                            println("$file: ${i + 1}:${strings[i]}")
                        }
                        else {
                            println("$file: ${i + 1}-${strings[i]}")
                        }
                    }
                    else {
                        if(i == count) {
                            println("${i + 1}:${strings[i]}")
                        }
                        else {
                            println("${i + 1}-${strings[i]}")
                        }
                    }
                }

                println()
            }
        }
    }
    else {
        val listOfLines: List<String> = file.readLines()

        for ((count, line) in listOfLines.withIndex()) {
            if (line.contains(Regex(regex))) {
                println("${count + 1}:$line")
            }
        }
    }
}
fun main() {
    print(">")
    init(readLine() ?: "")
}