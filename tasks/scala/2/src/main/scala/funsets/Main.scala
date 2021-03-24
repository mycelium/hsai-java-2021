package funsets

import FunSets._

object Main extends App {

  /**
   * Для тестов. Рекурсивный алгоритм Евклида
   * @param a первое число
   * @param b второе число
   * @return наибольший общий делитель чисел
   */
  def gcd(a: Int, b: Int): Int = {
    if (a <= 0 || b <= 0) 0
    else if (a == b) a
    else if (a > b) gcd(a - b, b)
    else gcd(b - a, a)
  }

  /**
   * НОК
   */
  def gcm(a: Int, b: Int): Int = {
    if (a <= 0 || b <= 0) 0
    else a * b / gcd(a, b)
  }

  /**
   * Множество чисел неотрицательных чисел, кратных a.
   */
  def multiples(a: Int): Set = (x: Int) => x % a == 0 && x >= 0

  /**
   * Первые a неотрицательных чисел.
   */
  def first(a: Int): Set = (x: Int) => 0 <= x && x < a

  /**
   * Отрезок целых чисел [a; b]
   */
  def range(a: Int, b: Int): Set = (x: Int) => a <= x && x <= b

  def isSubset(s: Set, t: Set) = forall(s, t)

  def equals(s: Set, t: Set) = isSubset(s, t) && isSubset(t, s)

  override def main(args: Array[String]): Unit = {
    println(contains(singletonSet(1), 1))
    println(gcd(15, 10))
    val n = 10
    val m = 15
    val A: Set = multiples(n)
    val B: Set = multiples(m)
    printSet(filter(A, first(100)))
    printSet(filter(B, first(100)))

    // Нахождение НОК == нахождение пересечений множетсва кратных
    println(equals(intersect(A, B),
      multiples(gcm(n, m))))

    // Оказывается, K(d) != K(m) U K(n), где d = gcd(n, m)!
    printSet(filter(diff(multiples(gcd(n, m)), union(A, B)), first(100)))

    printSet(filter(map(first(20), x => x * x),
      first(200)))
    val evens = multiples(2)

    // Квадраты четных чисел являются четными!
    println(isSubset(map(evens, x => x*x), evens))

    // Попробуем решить проблему Гольдбаха...
    println(s"Within $bound Goldbach conjecture is ...")
    // Простые числа
    val primes: Set = (x: Int) => x > 1 && forall(range(2, x - 1), a => x % a != 0)
    println(forall(filter(evens, x => x > 2), // для каждого чегного числа x больше двух
      (x: Int) => exists(primes,              // существуют простые числа p1 и p2
        (p1: Int) => exists(primes,           //
          (p2: Int) => x == p1 + p2))))       // что x = p1 + p2

    // Элементы множества хранятся, очевидно, в виде лямбда-выражений:
    println(s"All this time Set was a $primes")
    // Это, может, и эффективно в некоторых случаях по памяти,
    // но точно не по производительности:
    println("Trying to calculate f(f(f({0, 1, 2, 3}))), where f(x) = x + 1")
    printSet(map(map(map(first(4),
      x => x + 1),
      x => x + 1),
      x => x + 1))
    // На моем компьютере он так и не досчитал :(
  }
}
