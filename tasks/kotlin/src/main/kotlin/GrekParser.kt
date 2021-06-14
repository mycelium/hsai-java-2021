package wsyconan.kotlin.grek

import java.lang.IllegalArgumentException

class GrekParser constructor(s: String) {
    class GrekOptions {
        var a: Int = 0
        var b: Int = 0
        var path: String = ""
        var name: String = ""
        var pattern = Regex("")
        var recursive = false
    }

    private val command: String = s
    private val grek = GrekOptions()

    fun parse(): GrekOptions {
        val params: List<String> = command.split(' ')
        if(params.size<3){
            throw IllegalArgumentException("Options of command not enough!")
        }
        if (params[0] == "grek") {
            for (option in params.withIndex()) {
                when (option.value) {
                    "-n" -> grek.recursive = false
                    "-nr" -> grek.recursive = true
                    "-B" -> grek.b = params[option.index + 1].toInt()
                    "-A" -> grek.a = params[option.index + 1].toInt()
                    "grek" -> grek.name = option.value
                    else -> {
                        if(Regex("-[a-zA-Z0-9]*").matches(option.value)) {
                            throw IllegalArgumentException("Don't know what to do with ${option.value}")
                        }
                    }
                }
            }
        }
        grek.pattern = params[params.lastIndex - 1].toRegex()
        grek.path = params[params.lastIndex]
        return grek
    }
}