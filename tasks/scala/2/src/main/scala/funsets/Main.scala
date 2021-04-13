package funsets

object Main extends App {

  import FunSets._

  val s1 = singletonSet(1)
  val s2 = singletonSet(2)
  val s3 = singletonSet(3)
  val s12 = union(s1, s2)
  val s123 = union(s12, s3)
  println(contains(singletonSet(1), 1))
  println(equalsSet(s12, union(s1, s2)))
  // test union
  println()
  println("Test union()")
  printSet(s12)
  // test intersect
  println()
  println("Test intersect()")
  printSet(intersect(s12, s1))
  // test diff
  println()
  println("Test diff()")
  printSet(diff(s12, s1))
  // test filter
  println()
  println("Test filter()")
  printSet(filter(s123, (x: Int) => x >= 2))
  printSet(filter(s123, (x: Int) => x <= 3))
  // test forall
  println()
  println("Test forall()")
  println(forall(s123, (x: Int) => x >= 2))
  println(forall(s123, (x: Int) => x <= 3))
  // test exists
  println()
  println("Test exists()")
  println(exists(s123, (x: Int) => x > 2))
  println(exists(s12, (x: Int) => x > 3))
  // test map
  println()
  println("Test map()")
  printSet(map(s123, (x: Int) => x * 2))
}
