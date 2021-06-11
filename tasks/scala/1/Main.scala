package recfun

import scala.::

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    /*println()
    println(balance(List('(' , '0', '(')))
    println(balance(List('(' , '0', ')', ')')))

    println(countChange(200, List(5, 10)))*/
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {

    def factorial(num: Int): Int =
      if (num <= 1) 1 else num * factorial(num - 1)

    factorial(r) / (factorial(c) * factorial(r - c))
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def found(chars: List[Char], counter: Int): Boolean = chars match {
      case '(' :: tail => found(tail, counter + 1)
      case ')' :: tail => found(tail, counter - 1)
      case _ :: tail => found(tail, counter)
      case Nil => counter == 0
    }

    found(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def rec(money: Int, coinsInner: List[Int]): Int = {
      if (money <= 0) { if (money == 0) 1 else 0 }
      else {
        var counter = 0
        for (i <- coinsInner.indices) {
          counter += countChange(money - coinsInner(i), coinsInner.take(i + 1))
        }
        counter
      }
    }

    rec(money, coins.sorted)

  }

}
