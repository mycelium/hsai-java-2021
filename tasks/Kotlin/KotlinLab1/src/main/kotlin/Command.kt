class Command(val input: Array<String>) {
    var parN: Boolean = false
        get() = field
    var parR: Boolean = false
        get() = field
    var parA: Int = 0
        get() = field
    var parB: Int = 0
        get() = field
    var regEx: String = ""
        get() = field
    var parPath: String = ""
        get() = field

    enum class Options { N, NR, A, B }

    private val optionStrings: Map<String, Options> = mapOf(
        "-n" to Options.N,
        "-nr" to Options.NR,
        "-A" to Options.A,
        "-B" to Options.B
    )

    fun getOption(string: String): Options {
        var res: Options? = optionStrings[string]
        if (res == null) {
            throw RuntimeException("unknown option")
        } else {
            return res
        }
    }

    fun getPar() {
        if (input[0].equals("grek")) {
            print("wrong command")
        } else {
            for (i in 1..input.size-1) {
                if (input[i][0] == '-') {
                    val op: Options = getOption(input[i])
                    when (op) {
                        Options.N -> {
                            parN = true
                        }
                        Options.NR -> {
                            parN = true
                            parR = true
                        }
                        Options.A -> {
                            parA = input[i + 1].toInt()
                        }
                        Options.B -> {
                            parB = input[i + 1].toInt()
                        }
                    }
                }
            }
            regEx = input[input.size - 2]
            parPath = input[input.size - 1]
        }
    }

}