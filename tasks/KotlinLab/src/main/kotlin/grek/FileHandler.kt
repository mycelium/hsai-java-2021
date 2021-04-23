package grek

import java.io.File
import java.io.InputStream

class FileHandler(path: String) {
    private val file = File(path)

    private var strings: List<String> = try {
        file.readLines()
    } catch (ex: Exception) {
        emptyList<String>()
    }

    private var lastRead: Int = 0

    public fun exists(): Boolean = file.isFile

    public fun reachedEOF(): Boolean = lastRead == -1 || strings.isEmpty()

    public fun readNext(lines: Int): List<String> {
        if (lines < 1 || lastRead == -1) return emptyList()
        return if (lastRead + lines >= strings.size) readRest()
        else {
            val res = strings.subList(lastRead, lastRead + lines)
            lastRead += lines
            res
        }
    }

    public fun readRest(): List<String> {
        if (lastRead == -1) return emptyList()
        val res = strings.subList(lastRead, strings.size)
        lastRead = -1
        return res
    }
}