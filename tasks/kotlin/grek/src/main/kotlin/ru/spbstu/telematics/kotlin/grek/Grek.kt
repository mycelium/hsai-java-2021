package ru.spbstu.telematics.kotlin.grek

import java.io.File
import java.lang.IllegalArgumentException

class Grek(options: Map<CliParser.CliOption, String?>) {
    public enum class GrekStatus {
        SUCCESS,
        FAIL
    }

    val options = options

    public fun exec(): GrekStatus {
        if (!checkOptions()) {
            println("Invalid grep parameters")
            return GrekStatus.FAIL
        }

        val path: String = options[CliParser.CliOption.PATH].toString()
        val regexp = Regex(options[CliParser.CliOption.REGEXP].toString())
        val lineNumberMode: Boolean = options.containsKey(CliParser.CliOption.LINE_NUMBER)
        val recursiveMode: Boolean = options.containsKey(CliParser.CliOption.RECURSIVE)
        val contextBeforeMode: Boolean = options.containsKey(CliParser.CliOption.BEFORE)
        val contextAfterMode: Boolean = options.containsKey(CliParser.CliOption.AFTER)

        val contextBeforeValue: Int =
            if (contextBeforeMode) { options[CliParser.CliOption.BEFORE]?.toInt() ?: 0 } else { 0 }
        val contextAfterValue: Int =
            if (contextAfterMode) { options[CliParser.CliOption.AFTER]?.toInt() ?: 0 } else { 0 }

        val file: File = File(path)

        if (recursiveMode) {
            val dirTree = file.walkTopDown()
            for (subFile: File in dirTree.filter { file -> file.isFile }) {
                processFile(subFile, regexp, lineNumberMode, recursiveMode, contextAfterValue, contextBeforeValue)
            }
        }
        else {
            processFile(file, regexp, lineNumberMode, recursiveMode, contextAfterValue, contextBeforeValue)
        }

        return GrekStatus.SUCCESS
    }

    private fun processFile(file: File, regexp: Regex, n: Boolean, r: Boolean, A: Int, B: Int) {
        if (!file.isFile || !file.canRead()) {
            throw IllegalArgumentException("Can not read file")
        }

        val buffer = file.readLines()
        var lineCount: Int = 0;

        file.forEachLine { line ->
            if (regexp.containsMatchIn(line)) {
                val newLine = if (r) { "${file.path} " } else { "" } +
                        if (n) { "${lineCount + 1} \t" } else { "" } +
                        line

                if (B != 0) {
                    val startIdx = if (lineCount - B > 0) { lineCount - B } else { 0 }
                    for (lineIdx in startIdx until lineCount) {
                        val bufferLine = if (r) { "${file.path} " } else { "" } +
                                if (n) { "${lineIdx + 1} \t" } else { "" } +
                                buffer[lineIdx]

                        println(bufferLine)
                    }
                }

                println("\u001B[33m$newLine\u001B[0m")

                if (A != 0) {
                    val endIdx = if (lineCount + A < buffer.size) { lineCount + A } else { buffer.lastIndex }
                    for (lineIdx in (lineCount + 1)..endIdx) {
                        val bufferLine = if (r) { "${file.path} " } else { "" } +
                                if (n) { "${lineIdx + 1} \t" } else { "" } +
                                buffer[lineIdx]

                        println(bufferLine)
                    }
                }
                println()
            }
            lineCount++
        }
    }

    private fun checkOptions(): Boolean {
        val hasRegexp: Boolean = options.containsKey(CliParser.CliOption.REGEXP)
        val hasPath: Boolean = options.containsKey(CliParser.CliOption.PATH)

        if (!hasPath || !hasRegexp) {
            println("RegExp or File/Directory path is undefined")
            return false
        }

        val fileExists: Boolean = File(options[CliParser.CliOption.PATH].toString()).exists()

        if (!fileExists) {
            println("File or directory ${options[CliParser.CliOption.PATH].toString()} does not exist")
            return false
        }

        val isFile: Boolean = File(options[CliParser.CliOption.PATH].toString()).isFile

        if (options.containsKey(CliParser.CliOption.RECURSIVE) && isFile) {
            println("${options[CliParser.CliOption.PATH].toString()} is not a directory")
            return false
        }
        else if (!options.containsKey(CliParser.CliOption.RECURSIVE) && !isFile) {
            println("${options[CliParser.CliOption.PATH].toString()} is not a file")
            return false
        }

        return true
    }
}