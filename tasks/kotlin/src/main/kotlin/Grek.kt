package wsyconan.kotlin.grek

import java.io.File
import java.io.IOException
import kotlin.collections.ArrayList

class Grek constructor(private var grek: GrekParser.GrekOptions) {

    fun execute() {
        if (grek.recursive) matchFolder(grek.path)
        else matchFile(File(grek.path))
    }

    /**
     * Find matching lines from a file.
     * @param file: Path of file to be matched
     */
    private fun matchFile(file: File) {
        if(!file.exists()){
            throw IOException("File ${file.canonicalPath} not found!")
        }
        val ranges: ArrayList<IntRange> = ArrayList()
        val lines: List<String> = file.readLines()
        val lineMap = lines.associateBy {
            Pair(lines.indexOf(it), it)
        }
        // Locate range of indexes
        lineMap.forEach { (i, s) ->
            if (grek.pattern.matches(s)) {
                ranges.add(IntRange(i.first - grek.a, i.first + grek.a))
            }
        }
        val prints: List<Int> = ranges.flatten().distinct()
        println("Found from file ${file.canonicalPath}:")
        lineMap.forEach { (i, s) ->
            if (prints.contains(i.first)){
                println(s)
            }
        }
        println()
    }


    /**
     * Find matching lines from a folder.
     * @param path: path of folder.
     */
    private fun matchFolder(path: String) {
        val file = File(path)
        if(!file.exists()){
            throw IOException("Fold ${file.canonicalPath} does not exist!")
        }
        val fileTreeWalk: FileTreeWalk = file.walk()
        fileTreeWalk.maxDepth(1)
            .filter { it.isFile }
            .forEach { matchFile(it) }
    }
}