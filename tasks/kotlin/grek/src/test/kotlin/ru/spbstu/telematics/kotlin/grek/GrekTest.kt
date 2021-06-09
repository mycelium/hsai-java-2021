package ru.spbstu.telematics.kotlin.grek

import org.junit.Test
import kotlin.test.assertEquals

class GrekTest {
    val command1: Array<String> = arrayOf("-n", "-A", "2", "-B", "5", "abc", "src/test/resources/test0a.txt")
    val command2: Array<String> = arrayOf("-nr", "-A4", "[adef]+", "src/test/resources/testdir1")
    val command3: Array<String> = arrayOf("-A", "2", "-nr", "-B2", "pqrs", "src/test/resources/testdir1")

    val options1 = CliParser.parse(command1)
    val options2 = CliParser.parse(command2)
    val options3 = CliParser.parse(command3)

    @Test
    public fun grek1Test() {
        val grekInstance = Grek(options1)
        val status = grekInstance.exec()
        assertEquals(Grek.GrekStatus.SUCCESS, status)
    }

    @Test
    public fun grek2Test() {
        val grekInstance = Grek(options2)
        val status = grekInstance.exec()
        assertEquals(Grek.GrekStatus.SUCCESS, status)
    }

    @Test
    public fun grek3Test() {
        val grekInstance = Grek(options3)
        val status = grekInstance.exec()
        assertEquals(Grek.GrekStatus.SUCCESS, status)
    }
}