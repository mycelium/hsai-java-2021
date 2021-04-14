import java.io.File
import java.io.FileNotFoundException
import java.io.FileReader
import java.lang.Exception
import java.nio.file.Path

class Grek constructor()
{
    var keys: List<String> = emptyList();
    var regex: String = "";
    var path: String = "";
    var deepSearch : Boolean = false;
    var printLineNumber : Boolean = false;

    constructor(input : String) : this()
    {
        Parse(input);
    }

    fun Parse(line: String)
    {
        var words = line.split(" ").toTypedArray();

        if(words[0] != "grek")
        {
            throw Exception("Cannot parse nongrek as grek")
            return;
        }

        for(i in 1..words.size - 1)
        {
            when(words[i]) {
                "-n" -> {
                    println("Found -n key, will show line number now")
                    printLineNumber = true;
                    deepSearch = false;
                    keys += "-n";
                }
                "-r" -> {
                    println("Found -r key, will search deeper now")
                    printLineNumber = false;
                    deepSearch = true;
                    keys += "-r";
                }
                "-nr" ->
                {
                    println("Found -n and -r keys, will show line numbers and search deeper now")
                    deepSearch = true;
                    printLineNumber = true;
                    keys += "-nr";
                }
                "-A" -> {
                    println("Found strange key -A")
                }
                "-B" -> {
                    println("Found strange key -B")
                }
                else ->
                {
                    if(this.regex == "")
                    {
                        this.regex = words[i];
                        println("Entered regex: " + regex);
                    }
                    else
                    {
                        this.path = words[i];
                        println("Entered path: " + this.path);
                    }
                }
            }
        }

        println(line);
    }

    private fun findInFile(filename : String) : List<String>
    {
        var res = emptyList<String>();
        var inputFile : FileReader? = null;

        println(filename);

        try
        {
            inputFile = FileReader(filename);

            var n : Int = 1;

            inputFile.forEachLine {
                if(it.contains(this.regex))
                {
                    if(printLineNumber)
                    {
                        res += "Line $n: $it";
                    }
                    else
                    {
                        res += it;
                    }
                    n++;
                }
            };
        }
        catch (exc : FileNotFoundException)
        {
            println("Cannot search in file");
            println(exc);
        }
        finally {
            if (inputFile != null) {
                inputFile.close()
            };
        }

        return res;
    }

    private fun findInDir(dirname : String) : List<String>
    {
        var res = emptyList<String>();

        File(dirname).listFiles().forEach {
            if(it.isDirectory)
            {
                if(deepSearch)
                {
                    res += findInDir(dirname + "\\" + it.name)
                }
            }
            else {
                res += findInFile(dirname + "\\" + it.name);
            }
        }

        return res;
    }

    fun Find() : List<String>
    {
        var res = emptyList<String>();
        var inputFile : File? = null;

        try {
            inputFile  = File(path);
        }
        catch (exc : FileNotFoundException)
        {
            println("Cannot search");
            println("Ooops " + exc);
            return res;
        }

        if(inputFile.isDirectory)
        {
            res = findInDir(path);
        }
        else
        {
            res = findInFile(path);
        }

        return res;
    }

}