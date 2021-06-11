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
    var sum: Int = 0

    def found(c: Char): Int =
      c match {
        case '(' => 1
        case ')' => -1
        case _ => 0
      }

    def search(ind: Int): Unit = {
      sum += found(chars.apply(ind))
      if (ind < chars.length - 1) search(ind + 1)
    }

    search(0)

    sum == 0
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int =
    if (money < 0) 0 else if (money == 0) 1 else {

      var sum: Int = 0
      for (i <- coins.indices)
        sum += countChange(money - coins.apply(i), coins)

      sum

    }

}
