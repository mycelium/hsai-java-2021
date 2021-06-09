package ru.spbstu.telematics.kotlin.grek

object CliParser {
    public enum class CliOption {
        LINE_NUMBER,
        RECURSIVE,
        AFTER,
        BEFORE,
        REGEXP,
        PATH
    }

    private const val LINE_NUMBER_SYMBOL: String = "-n"
    private const val RECURSIVE_SYMBOL: String = "-nr"
    private const val AFTER_SYMBOL: String = "-A"
    private const val BEFORE_SYMBOL: String = "-B"

    public fun parse(args: Array<String>): Map<CliOption, String?> {
        if (args.isEmpty()) {
            throw IllegalArgumentException("Empty command")
        }

        var options = emptyMap<CliOption, String?>()

        var i = 0
        while (i < args.size) {
            when {
                /* Line Number Option */
                args[i] == LINE_NUMBER_SYMBOL -> {
                    if (options.containsKey(CliOption.LINE_NUMBER)) {
                        throw IllegalArgumentException("Option redefinition")
                    }
                    options = options.plus(CliOption.LINE_NUMBER to null)
                }
                /* Recursive Search Option */
                args[i] == RECURSIVE_SYMBOL -> {
                    if (options.containsKey(CliOption.RECURSIVE)) {
                        throw IllegalArgumentException("Option redefinition")
                    }
                    options = options.plus(CliOption.LINE_NUMBER to null)
                        .plus(CliOption.RECURSIVE to null)
                }
                /* After Context Option */
                args[i].startsWith(AFTER_SYMBOL) -> {
                    if (options.containsKey(CliOption.AFTER)) {
                        throw IllegalArgumentException("Option redefinition")
                    }
                    val optionParam = args[i].removePrefix(AFTER_SYMBOL)

                    try {
                        val parameter: Int
                        if (optionParam.isNotEmpty()) {
                            parameter = optionParam.toInt()
                            if (parameter < 1) throw IllegalArgumentException("Invalid option parameter")
                        } else if (optionParam.isEmpty() && (i < args.lastIndex)) {
                            parameter = args[i + 1].toInt()
                            if (parameter < 1) throw IllegalArgumentException("Invalid option parameter")
                            i++
                        } else {
                            throw IllegalArgumentException("Invalid option parameter")
                        }
                        options = options.plus(CliOption.AFTER to parameter.toString())
                    }
                    catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Invalid option parameter")
                    }
                }
                /* Before Context Option */
                args[i].startsWith(BEFORE_SYMBOL) -> {
                    if (options.containsKey(CliOption.BEFORE)) {
                        throw IllegalArgumentException("Option redefinition")
                    }
                    val optionParam = args[i].removePrefix(BEFORE_SYMBOL)

                    try {
                        val parameter: Int
                        if (optionParam.isNotEmpty()) {
                            parameter = optionParam.toInt()
                            if (parameter < 1) throw IllegalArgumentException("Invalid option parameter")
                        } else if (optionParam.isEmpty() && (i < args.lastIndex)) {
                            parameter = args[i + 1].toInt()
                            if (parameter < 1) throw IllegalArgumentException("Invalid option parameter")
                            i++
                        } else {
                            throw IllegalArgumentException("Invalid option parameter")
                        }
                        options = options.plus(CliOption.BEFORE to parameter.toString())
                    }
                    catch (e: NumberFormatException) {
                        throw IllegalArgumentException("Invalid option parameter")
                    }
                }
                /* End of options */
                else -> {
                    break
                }
            }
            i++
        }

        if (i < args.size) {
            options = options.plus(CliOption.REGEXP to args[i++])
        }
        else {
            throw IllegalArgumentException("No regexp defined")
        }

        if (i < args.size) {
            options = options.plus(CliOption.PATH to args[i])
        }
        else {
            throw IllegalArgumentException("No file path defined")
        }

        if (i >= args.size) {
            throw IllegalArgumentException("Too many arguments")
        }

        return options
    }
}