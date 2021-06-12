import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class GrekArgs(parser: ArgParser) {

    val before by parser.storing(
        "-B", "--before",
        help = "number of lines to output before match") { toInt() }.default(0)

    val after by parser.storing(
        "-A", "--after",
        help = "number of lines to output after match") { toInt() }.default(0)

    val numberLines by parser.flagging(
        "-n",
        help = "display lines number").default(false)

    val recursive by parser.flagging(
        "-r",
        help = "process folder recursively").default(false)

    val regex by parser.positional("REGEX",
        help = "regex to search") {Regex(this)}

    val path by parser.positional("PATH",
        help = "path to file or folder to process")

}