@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package main

import java.io.File
import java.util.regex.PatternSyntaxException

class Grek {
    var regex: String = ""
    var fileName: String = ""
    var nParam: Boolean = false
    var rParam: Boolean = false
    var bParam: Boolean = false
    var bValue: Int = 0
    var aParam: Boolean = false
    var aValue: Int =0
}

fun initiateGrek(string: String): String{
    val grek = Grek()
    val params : List<String> = string.split(' ')
    if (params[0] == "grek" && params.size >= 4) {
        for (strings in params.withIndex()) {
            when (strings.value) {
                "-n" -> grek.nParam = true
                "-nr" -> grek.rParam = true
                "-B" -> {
                    grek.bParam = true
                    grek.bValue = params[strings.index + 1].toInt()
                }
                "-A" -> {
                    grek.aParam = true
                    grek.aValue = params[strings.index + 1].toInt()
                }
            }
        }
        grek.fileName = params[params.lastIndex]
        grek.regex = params[params.lastIndex - 1]
    }
    return runGrek(grek)
}

fun runGrek(grek: Grek): String {
    try {
        Regex(grek.regex)
    } catch (ex: PatternSyntaxException) {
        return "Incorrect regular expression"
    }
    if(File(grek.fileName).isFile || File(grek.fileName).isDirectory) {
        if (grek.aParam && grek.bParam && grek.aValue>0 && grek.bValue>0) {
            if (grek.nParam && File(grek.fileName).isFile)
                return findMatchesWithBounds(grek.bValue, grek.aValue, grek.regex, File(grek.fileName), false)

            if (grek.rParam && File(grek.fileName).isDirectory)
                return findMatchesRecursiveWithBounds(grek.regex, File(grek.fileName), grek.bValue, grek.aValue)
        }
        else {
            if (grek.nParam && File(grek.fileName).isFile)
                return findMatches(grek.regex, File(grek.fileName), false)

            if (grek.rParam && File(grek.fileName).isDirectory)
                return findMatchesRecursive(grek.regex, File(grek.fileName))
        }
    }
    return "No such file or directory"
}

fun findFilesRecursive(files: MutableList<File>, directory: File){
    for (item in directory.listFiles()) {
        if (item.isDirectory) {
            findFilesRecursive(files, item)
        } else {
            files.add(item)
        }
    }
}

fun findMatchesRecursive(regex: String, path: File): String{
    var result=""
    val files: MutableList<File> = mutableListOf()
    findFilesRecursive(files, path)
    for (file in files){
        result+= findMatches(regex, file, true)
    }
    return result
}

fun findMatchesRecursiveWithBounds(regex: String, path: File, before: Int, after: Int): String{
    var result=""
    val files: MutableList<File> = mutableListOf()
    findFilesRecursive(files, path)
    for (file in files){
        result+= findMatchesWithBounds(before, after, regex, file, true)
    }
    return result
}

fun findMatches(regex: String, file: File, isRecursive: Boolean): String{
    var result=""
    val strings: List<String> = file.readLines()
    for ((count, line) in strings.withIndex()) {
        if (line.contains(Regex(regex))) {
            result += if (isRecursive) {
                "$file:${count + 1}:$line\n"
            } else "${count + 1}:$line\n"
        }
    }
    return result
}

fun findMatchesWithBounds(before: Int, after: Int, regex: String, file: File, recursive:Boolean): String{
    var result=""
    val strings: List<String> = file.readLines()
    for ((count, line) in strings.withIndex()) {
        if (line.contains(Regex(regex))) {
            for (i in maxOf(count - before, 0)..minOf(count + after, strings.size-1)) {
                result += if (recursive) {
                    if (i == count)
                        "$file:${i + 1}:${strings[i]}\n"
                    else "$file:${i + 1}-${strings[i]}\n"
                } else {
                    if(i==count)
                        "${i + 1}:${strings[i]}\n"
                    else "${i + 1}-${strings[i]}\n"
                }
            }
            result+="--\n"
        }
    }
    return result
}

