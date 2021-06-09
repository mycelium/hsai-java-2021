package ru.spbstu.telematics.kotlin.grek

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class CliParserTest {
    val command1: Array<String> = arrayOf("-n", "-A", "4", "-B", "5", "abcd", "filename")
    val command2: Array<String> = arrayOf("-nr", "-A4", "-B5", "abcd", "filename")
    val command3: Array<String> = arrayOf("-A", "4", "-nr", "-B5", "abcd", "filename")
    val command4: Array<String> = arrayOf("-A", "4", "-nr", "-B5", "abcd", "filename", "garbage")
    val command5: Array<String> = arrayOf("abcd", "filename", "garbage")
    val command6: Array<String> = arrayOf("-A-2", "-nr", "-B5", "abcd", "filename", "garbage")

    @Test
    public fun parse1Test1() {
        val options1 = CliParser.parse(command1)
        assertTrue { options1.containsKey(CliParser.CliOption.LINE_NUMBER) }
        assertEquals(null, options1[CliParser.CliOption.LINE_NUMBER])
        assertFalse { options1.containsKey(CliParser.CliOption.RECURSIVE) }
        assertTrue { options1.containsKey(CliParser.CliOption.AFTER) }
        options1[CliParser.CliOption.AFTER]?.let { assertEquals(4, it.toInt()) }
        assertTrue { options1.containsKey(CliParser.CliOption.BEFORE) }
        options1[CliParser.CliOption.BEFORE]?.let { assertEquals(5, it.toInt()) }
        assertTrue { options1.containsKey(CliParser.CliOption.REGEXP) }
        assertEquals("abcd", options1[CliParser.CliOption.REGEXP])
        assertTrue { options1.containsKey(CliParser.CliOption.PATH) }
        assertEquals("filename", options1[CliParser.CliOption.PATH])
    }

    @Test
    public fun parse2Test() {
        val options2 = CliParser.parse(command2)
        assertTrue { options2.containsKey(CliParser.CliOption.LINE_NUMBER) }
        assertEquals(null, options2[CliParser.CliOption.LINE_NUMBER])
        assertTrue { options2.containsKey(CliParser.CliOption.RECURSIVE) }
        assertEquals(null, options2[CliParser.CliOption.RECURSIVE])
        assertTrue { options2.containsKey(CliParser.CliOption.AFTER) }
        options2[CliParser.CliOption.AFTER]?.let { assertEquals(4, it.toInt()) }
        assertTrue { options2.containsKey(CliParser.CliOption.BEFORE) }
        options2[CliParser.CliOption.BEFORE]?.let { assertEquals(5, it.toInt()) }
        assertTrue { options2.containsKey(CliParser.CliOption.REGEXP) }
        assertEquals("abcd", options2[CliParser.CliOption.REGEXP])
        assertTrue { options2.containsKey(CliParser.CliOption.PATH) }
        assertEquals("filename", options2[CliParser.CliOption.PATH])
    }

    @Test
    public fun parse3Test() {
        val options2 = CliParser.parse(command3)
        assertTrue { options2.containsKey(CliParser.CliOption.LINE_NUMBER) }
        assertEquals(null, options2[CliParser.CliOption.LINE_NUMBER])
        assertTrue { options2.containsKey(CliParser.CliOption.RECURSIVE) }
        assertEquals(null, options2[CliParser.CliOption.RECURSIVE])
        assertTrue { options2.containsKey(CliParser.CliOption.AFTER) }
        options2[CliParser.CliOption.AFTER]?.let { assertEquals(4, it.toInt()) }
        assertTrue { options2.containsKey(CliParser.CliOption.BEFORE) }
        options2[CliParser.CliOption.BEFORE]?.let { assertEquals(5, it.toInt()) }
        assertTrue { options2.containsKey(CliParser.CliOption.REGEXP) }
        assertEquals("abcd", options2[CliParser.CliOption.REGEXP])
        assertTrue { options2.containsKey(CliParser.CliOption.PATH) }
        assertEquals("filename", options2[CliParser.CliOption.PATH])
    }

    @Test
    public fun parse4Test() {
        try {
            CliParser.parse(command4)
        }
        catch (e: IllegalArgumentException) {
            assert(true)
        }
    }

    @Test
    public fun parse5Test() {
        try {
            CliParser.parse(command5)
        }
        catch (e: IllegalArgumentException) {
            assert(true)
        }
    }

    @Test
    public fun parse6Test() {
        try {
            CliParser.parse(command6)
        }
        catch (e: IllegalArgumentException) {
            assert(true)
        }
    }
}