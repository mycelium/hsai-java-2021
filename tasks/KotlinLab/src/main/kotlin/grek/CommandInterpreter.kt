package grek

import java.lang.IllegalArgumentException

public class CommandInterpreter constructor(private val input: List<String>) {
    var log: String = ""
        get() = field
    var parA: Int = 0
        get() = field
    var parB: Int = 0
        get() = field
    var parRegex: String = ""
        get() = field
    var parPath: String = ""
        get() = field
    var parSingleFile: Boolean = false
        get() = field


    enum class Options { N, NR, A, B }

    private val optionStrings: Map<String, Options> = mapOf("-nr" to Options.NR,
                                                    "-n" to Options.N,
                                                    "-A" to Options.A,
                                                    "-B" to Options.B)

    var correct: Boolean = translate()
        get() = field

    private fun getOption(string: String): Options {
        if (string.isEmpty() || string[0] != '-')
            throw IllegalArgumentException("Expected an option, got: $string")
        val res: Options? = optionStrings[string]
        if (res == null) throw IllegalArgumentException("Unknown option: $string")
        else return res
    }

    private fun getNum(string: String): Int {
        try {
            val res = string.toInt()
            if (res < 1) throw IllegalArgumentException("Number parameter must be positive, got: $res")
            return res
        } catch (ex: NumberFormatException) {
            throw IllegalArgumentException("Expected number, got: $string")
        }
    }

    private fun translate(): Boolean {
        var i = 0
        try {
            loop@ while (i < input.size) {
                val op: Options = getOption(input[i])
                when (op) {
                    Options.N, Options.NR -> {
                        if (input.size - i == 3) {
                            parSingleFile = op == Options.N
                            parRegex = input[i + 1]
                            parPath = input[i + 2]
                            break@loop
                        } else {
                            throw IllegalArgumentException("Wrong number of arguments")
                        }
                    }
                    Options.A -> {
                        if (input.size - i > 1) {
                            val par = getNum(input[i + 1])
                            if (parA > 0) throw IllegalArgumentException("Redefining -A")
                            parA = par
                            i += 2
                        } else throw IllegalArgumentException("Expected value after -A")
                    }
                    Options.B -> {
                        if (input.size - i > 1) {
                            val par = getNum(input[i + 1])
                            if (parB > 0) throw IllegalArgumentException("Redefining -B")
                            parB = par
                            i += 2
                        } else throw IllegalArgumentException("Expected value after -B")
                    }
                }
            }
            if (parPath.isEmpty() || parRegex.isEmpty()) {
                throw IllegalArgumentException("Expected regex and path")
            }
        } catch (ex: IllegalArgumentException) {
            log = ex.message ?: "Translation successful"
            return false
        }
        log = "Translation successful"
        return true
    }
}