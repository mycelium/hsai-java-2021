package wsyconan.kotlin.grek

fun main(str: String) {
    val grek = Grek(GrekParser(str).parse())
    grek.execute()
}