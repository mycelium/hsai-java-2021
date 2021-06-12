import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.UnrecognizedOptionException
import java.io.File
import java.io.FileNotFoundException
import java.lang.Integer.max
import java.lang.Integer.min


fun matchLine(line:String, regex:Regex): List<IntRange> {
    return regex.findAll(line).map { it.range }.toList()
}

fun matchFile(pathToFile:String, regex:Regex, before:Int=0, after:Int=0, numbered:Boolean=false): String {
    var fileMatchContent = "Matching file"
    val fileContent = File(pathToFile).readLines()
    fileMatchContent += fileContent.mapIndexed { index, line ->
        listOf(index).flatMap { lhsElem -> matchLine(line, regex).map { rhsElem -> lhsElem to rhsElem } } }.
    flatten().map {match -> printContent(fileContent, match, before, after, numbered) }.
    joinToString(separator="")
    return fileMatchContent
}

fun printContent(fileContent:List<String>, match:Pair<Int, IntRange>, before:Int=0, after:Int=0, numbered:Boolean=false) : String {
    var matchContent = ""
    matchContent += printLines(fileContent.subList(max(0, match.first - before), match.first),
        if (numbered) match.first - before else -1)
    matchContent += printMatchLine(fileContent[match.first], match.second, if (numbered) match.first else -1) + "\n"
    matchContent += printLines(fileContent.subList(match.first + 1, min(match.first + 1 + after, fileContent.size)),
        if (numbered) match.first + 1 else -1)
    matchContent += ""
    return matchContent
}

fun printLines(lines:List<String>, startIndex:Int) : String {
    return lines.mapIndexed { index, line -> if (startIndex > -1) startIndex + index + 1
    else line + "\n" }.joinToString(separator="")
}

fun printMatchLine(line:String, match:IntRange, lineIndex:Int) : String {
    var matchLineContent = ""
    if (lineIndex > -1) matchLineContent += lineIndex + 1
    matchLineContent += line.substring(0, match.first)
    matchLineContent += line.substring(match.first, match.last + 1)
    matchLineContent += line.substring(match.last + 1, line.length)
    return matchLineContent
}

fun matchFolder(pathToFolder: String, regex:Regex, before:Int=0, after:Int=0, numbered:Boolean=false) : String {
    return File(pathToFolder).walkTopDown().toList().parallelStream().filter {it.isFile}.
    map { matchFile(it.path, regex,  before, after, numbered)}.toList().joinToString("\n")
}