package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (r < 0) 0
    else if (r == 0) {
      if (c == 0) 1 else 0
    }
    else {
      if (c < 0) 0 else pascal(c, r - 1) +
        pascal(c - 1, r - 1)
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def recursion(chars: List[Char], counter: Int): Boolean = {
      chars match {
        case Nil => counter == 0
        case '(' :: (rest: List[Char]) => recursion(rest, counter + 1)
        case ')' :: (rest: List[Char]) =>
          if (counter > 0)
            recursion(rest, counter - 1)
          else
            false
        case _ :: (rest: List[Char]) => recursion(rest, counter)
        case _ => false
      }
    }
    recursion(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def recursion(money: Int, coins: List[Int]): Int = {
      if (money == 0) 1
      else if (money < 0) 0
      else coins match {
        case Nil => 0
        case x :: Nil => if (money % x == 0) 1 else 0
        case x :: rest => recursion(money - x, coins) +
          recursion(money, rest)
      }
    }
    if (money <= 0) 0
    else recursion(money,
      coins.distinct                    // (1)
        .filter(_ > 0)                  // (2)
        .sorted(Ordering[Int].reverse)) // (3)

    // (1) выкидывать повторяющиеся элементы может и не нужно,
    //     зависит от спецификации
    // (2) при наличии отрицательных элементов алгоритм не
    //     завершится, я решил их выкинуть, т.к. спецификации
    //     нет
    // (3) сортировка по убыванию заметно уменьшает количество
    //     вызывов
  }
}
