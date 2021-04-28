package funsets

object Main extends App {

  import FunSets._

  println(contains(singletonSet(1), 1))

  println("\nunion testing")
  println(contains(union(singletonSet(3), singletonSet(5)), 5))
  println(contains(union(singletonSet(3), singletonSet(5)), 3))
  println(contains(union(singletonSet(3), singletonSet(5)), 4))

  println("\nintersect testing")
  println(contains(intersect(union(singletonSet(3), singletonSet(5)), singletonSet(6)), 6))
  println(contains(intersect(union(singletonSet(3), singletonSet(5)), singletonSet(6)), 5))
  println(contains(intersect(union(singletonSet(3), singletonSet(5)), singletonSet(6)), 3))
  println(contains(intersect(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 3))
  println(contains(intersect(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 5))

  println("\ndiff testing")
  println(contains(diff(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 5))
  println(contains(diff(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 3))

  println("\nfilter testing")
  println(contains(filter(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 3))
  println(contains(filter(union(singletonSet(3), singletonSet(5)), singletonSet(3)), 5))

  println("\nforall testing")
  println(forall(union(singletonSet(3), singletonSet(5)), singletonSet(3)))
  println(forall(singletonSet(3), union(singletonSet(3), singletonSet(5))))
  println(forall(singletonSet(-1500), union(singletonSet(1), singletonSet(56))))
  println(forall(union(singletonSet(1), singletonSet(1300)), union(singletonSet(1), singletonSet(1500))))
  println(forall(union(singletonSet(2), singletonSet(1300)), union(singletonSet(1), singletonSet(1500))))

  println("\nexists testing")
  println(exists(union(singletonSet(1), singletonSet(1300)), union(singletonSet(1), singletonSet(2))))
  println(exists(union(singletonSet(-1010), singletonSet(1300)), union(singletonSet(1), singletonSet(2))))

  println("\nmap testing")
  println(contains(map(union(singletonSet(3), singletonSet(5)), x => x+1), 3))
  println(contains(map(union(singletonSet(3), singletonSet(5)), x => x+1), 4))
  println(contains(map(union(singletonSet(3), singletonSet(5)), x => x+1), 5))
  println(contains(map(union(singletonSet(3), singletonSet(5)), x => x+1), 6))
}