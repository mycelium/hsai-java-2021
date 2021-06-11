import Grek;

fun main(args : Array<String>)
{
    var grek : Grek;

    if(args.isEmpty()) {
        var path = System.getProperty("user.dir");

        println(path);

        //var grek = Grek("grek -n one $path\\file.txt");
        grek = Grek("grek -nr one $path\\Resources");
    }
    else
    {
        var grekArg = ""
        for (word in args)
        {
            grekArg = grekArg + " " + word;
        }

        grekArg = grekArg.removeRange(0, 1)

        grek = Grek(grekArg)
    }

    var lines = grek.Find();

    for(line in lines)
    {
        println(line);
    }
}
