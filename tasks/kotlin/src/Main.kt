import Grek;

fun main()
{
    var path = System.getProperty("user.dir");

    println(path);

    //var grek = Grek("grek -n one $path\\file.txt");
    var grek = Grek("grek -nr one $path\\Resources");

    var lines = grek.Find();

    for(line in lines)
    {
        println(line);
    }
}
