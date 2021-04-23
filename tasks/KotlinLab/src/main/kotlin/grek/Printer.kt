package grek

import java.io.PrintStream

class Printer(private val out: PrintStream) {
    fun printResult(lines: List<String>, regex: String) {
        val rx = Regex(regex)
        for(s in lines) {
            if (s.isNotEmpty()) out.println(s)
            val entries = rx.findAll(s).map { m -> m.range.first }.toList()
            if (entries.isNotEmpty()) {
                var count = 0
                var last = -1
                entries.forEach{i ->
                    run {
                        out.print(" ".repeat(i - count))
                        out.print('^')
                        count += i - last
                        last = i
                    }
                }
                out.println()
            }
        }
    }

    fun printFileName(path: String) = out.println("File: $path".replace("\\", "/"))

    fun printDelimiter() = out.println("<...>")
}