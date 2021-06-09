import java.io.File
import java.util.*

class Grek constructor(s: String) {
    private var command: String = ""
    private var a: Int = 0
    private var b: Int = 0
    private var path: String = ""
    private var pattern: Regex = Regex("")
    private var fileMode = true

    init {
        command = s
    }

    fun execute() {
        val params: List<String> = command.split(' ')
        if (params[0] == "grek" && params.size >= 4) {
            for (strings in params.withIndex()) {
                when (strings.value) {
                    "-n" -> fileMode = true
                    "-nr" -> fileMode = false
                    "-B" -> b = params[strings.index + 1].toInt()
                    "-A" -> a = params[strings.index + 1].toInt()
                }
            }
        }
        pattern = params[params.lastIndex - 1].toRegex()
        path = params[params.lastIndex]
        if (fileMode) matchFile(File(path))
        else matchFolder()
    }

    /**
     * Find matching lines from a file.
     * @param file: Path of file to be matched
     */
    private fun matchFile(file: File) {
        val abQueue: Queue<String> = LinkedList()
        var oldSize: Int = abQueue.size
        var found = false
        file.forEachLine {
            if (!found) {
                if (abQueue.size > oldSize + b + 1) {
                    abQueue.remove()
                }
                abQueue.add(it)
                if (pattern.matches(it)) {
                    found = true
                    oldSize = abQueue.size
                }
            } else {
                if (abQueue.size < oldSize + a) {
                    abQueue.add(it)
                }
                if (abQueue.size == oldSize + a) {
                    found = false
                    oldSize = abQueue.size
                }
            }
        }
        if (found) {
            oldSize = abQueue.size
        }
        if (!abQueue.isEmpty()) {
            println("From file ${file.canonicalPath} found: ")
            for (i in 1..oldSize) {
                println(abQueue.poll())
            }
        }
    }

    /**
     * Find matching lines from a folder.
     */
    private fun matchFolder() {
        val fileTreeWalk: FileTreeWalk = File(path).walk()
        fileTreeWalk.maxDepth(1)
            .filter { it.isFile }
            .filter { it.extension == "txt" }
            .forEach { matchFile(it) }
    }
}