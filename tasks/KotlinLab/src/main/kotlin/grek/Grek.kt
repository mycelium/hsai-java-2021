package grek

import java.io.File
import java.io.PrintStream
import java.lang.Integer.max
import java.lang.Integer.min

class Grek(val outputStream: PrintStream = System.out) {
    fun run(args: Array<String>) {
        val commandInterpreter = CommandInterpreter(args.toList())
        if (!commandInterpreter.correct) {
            outputStream.println("Grek error: invalid command syntax: ${commandInterpreter.log}")
            return
        }
        val filePaths = ArrayList<String>()
        val matcher = RegexMatcher(commandInterpreter.parRegex)
        val a = commandInterpreter.parA
        val b = commandInterpreter.parB
        val out = Printer(outputStream)
        if (commandInterpreter.parSingleFile) {
            filePaths.add(commandInterpreter.parPath)
        }
        else {
            val file = File(commandInterpreter.parPath)
            if (!file.isDirectory) {
                System.err.println("Grek error: ${file.path} is not a directory!")
                return
            }
            for (f in file.walkTopDown()) filePaths.add(f.path)
        }
        for (path in filePaths) {
            val handler = FileHandler(path)
            if (handler.exists()) {
                val strings = handler.readRest()
                val matches = matcher.findMatches(strings)
                if (matches.isNotEmpty()) {
                    out.printFileName(path)
                    out.printDelimiter()
                    val it = matches.iterator()
                    var left = 0
                    var right = 0
                    while (it.hasNext()) {
                        val nextIndex = it.next()
                        if (right >= nextIndex - b)
                            right = min(strings.size, nextIndex + a + 1)
                        else {
                            if (right > 0) {
                                out.printResult(strings.subList(left, right),
                                    commandInterpreter.parRegex)
                                out.printDelimiter()
                            }
                            left = max(0, nextIndex - b)
                            right = min(strings.size, nextIndex + a + 1)
                        }
                    }
                    out.printResult(strings.subList(left, right),
                        commandInterpreter.parRegex)
                    out.printDelimiter()
                }
            }
            else if (commandInterpreter.parSingleFile) {
                System.err.println("Grek error: file $path does not exist!")
            }
        }
    }
}