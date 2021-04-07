package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  var firstSet : Set = Set(1, 2, 3, 4);
  var secondSet : Set = Set(2, 4, 5, 6, 7, 8);

  println(firstSet);
  println(secondSet);
  println();

  printSet(union(firstSet, secondSet));
  println();

  printSet(intersect(firstSet, secondSet));
  println();

  printSet(diff(firstSet, secondSet));
  println();

  println(exists(firstSet, (x : Int) => (x >= 5) && (x <= 8)));
  println(exists(secondSet, (x : Int) => (x >= 1) && (x <= 4)));
  println();

  println(forall(firstSet, (x : Int) => (x >= 1) && (x <= 4)));
  println(forall(secondSet, (x : Int) => (x >= 1) && (x <= 4)));
  println();

  printSet(map(firstSet, (x : Int) => x * 2));
  println();
}
