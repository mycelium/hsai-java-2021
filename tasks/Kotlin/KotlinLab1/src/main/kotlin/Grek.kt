import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors

import java.util.stream.Stream
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.readLines


class Grek(var args: Array<String>) {
    fun run() {
        var command: Command = Command(args)
        command.getPar()
        if (!Files.isDirectory(Paths.get(command.parPath))) {
            checkFile(command, Paths.get(command.parPath))
        } else {
            if(command.parR) {
                File(command.parPath).walkTopDown()
                    .map {file -> file.toPath() }
                    .filter { file -> !Files.isDirectory(file) }
                    .forEach { file -> checkFile(command, file) }
            } else{
                Stream.of<File>(*File(command.parPath).listFiles())
                    .filter { file: File -> !file.isDirectory }
                    .map { file -> file.toPath() }
                    .collect(Collectors.toSet())
                    .forEach{ file -> checkFile(command, file) }
            }
        }
    }

    @OptIn(ExperimentalPathApi::class)
    fun checkFile(command: Command, file: Path) {
        val readLines = file.readLines()
        var regex: Regex = Regex(command.regEx)
        var numerate: Int = 0
        var isPrinted: ArrayList<Int> = ArrayList()
        while (numerate < readLines.size) {
            var line = readLines.get(numerate)
            if (line.matches(regex)) {
                if (command.parB > 0) {
                    if (numerate - command.parB <= 0) {
                        for (i in 0..numerate) {
                            if (!isPrinted.contains(i)) {
                                line = readLines.get(i)
                                print(command, line, i)
                                isPrinted.add(i)
                            }
                        }
                    } else {
                        for (i in numerate - command.parB..numerate) {
                            if (!isPrinted.contains(i)) {
                                line = readLines.get(i)
                                print(command, line, i)
                                isPrinted.add(i)
                            }
                        }
                    }
                }
                if (command.parA > 0) {
                    if (numerate + command.parA >= readLines.size) {
                        for (i in numerate..readLines.size - 1) {
                            if (!isPrinted.contains(i)) {
                                line = readLines.get(i)
                                print(command, line, i)
                                isPrinted.add(i)
                            }
                        }
                    } else {
                        for (i in numerate..numerate + command.parA) {
                            if (!isPrinted.contains(i)) {
                                line = readLines.get(i)
                                print(command, line, i)
                                isPrinted.add(i)
                            }
                        }
                    }
                }
                if (command.parA == 0 && command.parB == 0) {
                    if (!isPrinted.contains(numerate)) {
                        print(command, line, numerate)
                        isPrinted.add(numerate)
                    }
                }
            }
            numerate++
        }
    }

    fun comA(command: Command, readLines: List<String>) {
        var regex: Regex = Regex(command.regEx)
        var numerate: Int = 0
        var isPrinted: ArrayList<Int> = ArrayList()
        var iter: Int = 0
        while (iter < readLines.size) {
            var line = readLines.get(iter)
            if (command.parA > 0 && line.matches(regex)) {
                if (numerate + command.parA >= readLines.size) {
                    for (i in numerate..readLines.size - 1) {
                        if (!isPrinted.contains(i)) {
                            print(command, line, i)
                            if (iter + 1 != readLines.size) {
                                iter++
                                line = readLines.get(iter)
                            }
                            isPrinted.add(i)
                        }
                    }
                } else {
                    if (line.matches(regex)) {
                        for (i in numerate..numerate + command.parA) {
                            if (!isPrinted.contains(i)) {
                                print(command, line, i)
                                if (iter + 1 != numerate + command.parA) {
                                    iter++
                                    line = readLines.get(iter)
                                }
                                isPrinted.add(i)
                            }
                        }
                    }
                }
            }
            iter++
            numerate++
        }
    }

    fun print(command: Command, line: String, num: Int) {
        if (command.parN) {
            var temp: Int=num+1
            println("$temp: $line")
        } else {
            println("$line")
        }
    }
}