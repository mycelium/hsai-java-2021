package ru.spbstu.telematics.kotlin.grek

fun main(args: Array<String>) {
    try {
        val options = CliParser.parse(args)
        val grek = Grek(options)
        grek.exec()
    }
    catch (e: IllegalArgumentException) {
        println("Invalid command syntax: ${e.message}")
    }
}