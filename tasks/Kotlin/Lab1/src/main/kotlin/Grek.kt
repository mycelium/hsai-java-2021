import java.io.File

data class GrekParams(
    var regex: Regex = Regex(""),
    var path: String = "",
    var printLines: Boolean = false,
    var recursive: Boolean = false,
    var afterContext: Int = 0,
    var beforeContext: Int = 0
)

fun createParams(args: Array<String>): GrekParams {
    val params = GrekParams()
    for ((index, value) in args.withIndex()) {
        when (value) {
            "-n" -> params.printLines = true
            "-r" -> params.recursive = true
            "-nr" -> {
                params.printLines = true
                params.recursive = true
            }
            "-rn" -> {
                params.printLines = true
                params.recursive = true
            }
            "-A" -> params.afterContext = args[index + 1].toInt()
            "-B" -> params.beforeContext = args[index + 1].toInt()
        }
    }
    params.regex = args[args.lastIndex - 1].toRegex()
    params.path = args[args.lastIndex]
    return params
}

class Grek() {
    fun run(params: GrekParams) {
        val file = File(params.path)
        if (!file.exists() || !file.isDirectory() && !file.isFile()) {
            throw IllegalArgumentException("${params.path} does not exist")
        }
        val context = Pair(params.beforeContext, params.afterContext)
        if (file.isDirectory) {
            printMatchedDirectoryContent(file, params.regex, params.printLines, context, params.recursive)
        } else {
            printMatchedFileContent(file, params.regex, params.printLines, context)
        }
    }

    private fun printMatchedDirectoryContent(directory: File, regex: Regex, printLines: Boolean, context: Pair<Int, Int>, recursive: Boolean) {
        val files = directory.listFiles()
        if (files.isEmpty()) {
            return
        }
        for (file in files) {
            if (file.isDirectory() && recursive) {
                printMatchedDirectoryContent(file, regex, printLines, context, recursive)
            }
            if (file.isFile()) {
                println(file.absolutePath)
                printMatchedFileContent(file, regex, printLines, context)
            }
        }
    }

    private fun printMatchedFileContent(file: File, regex: Regex, printLines: Boolean, context: Pair<Int, Int>) {
        val (beforeBound, afterBound) = context
        val lines = file.readLines()
        for(lineIndex in lines.indices) {
            regex.find(lines[lineIndex])?.let {
                val contextRange = maxOf(lineIndex - beforeBound, 0)..minOf(lineIndex + afterBound, lines.size - 1)
                for (contextLineIndex in contextRange) {
                    if (printLines) {
                        val line = contextLineIndex + 1;
                        print("$line: ")
                    }
                    println(lines[contextLineIndex])
                }
                println()
            }
        }
    }
}
