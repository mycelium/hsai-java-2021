import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.UnrecognizedOptionException
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    try {
        val parsedArgs = ArgParser(args).parseInto(::GrekArgs)
        if (parsedArgs.recursive)
            print(matchFolder(parsedArgs.path, parsedArgs.regex, parsedArgs.before, parsedArgs.after, parsedArgs.numberLines))
        else
            print(matchFile(parsedArgs.path, parsedArgs.regex, parsedArgs.before, parsedArgs.after, parsedArgs.numberLines))
    }
    catch (e: UnrecognizedOptionException) {
        print("UnrecognizedOptionExceptionCaught! " +
                "You have probably entered unexisting command line argument!")
        print(e.message)
    }
    catch (e: FileNotFoundException) {
        print("File does not exist! Check your file path!")
        print(e.message)

    }
    catch (e: Exception) {
        print("Exception caught!")
        print(e.message)
    }
}


