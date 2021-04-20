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

    println("\nParentheses Balancing")
    println(balance("())(()".toList))


    println("[Counting section]")

    print("Value : 12, coins : 2, 3 -> number of ways to get value = ")
    println(countChange(12, List(2, 3)))

    print("Value : 256, coins : 2, 4 -> number of ways to get value = ")
    println(countChange(256, List(2, 4)))  //1

  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def check(chars_list: List[Char], n: Int): Boolean = chars_list match {
      case '(' :: rest => check(rest, n + 1)
      case ')' :: rest => if (n < 1) false else check(rest, n - 1)
      case _ :: rest => check(rest, n)
      case Nil => n == 0
    }
    check(chars, 0)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def count(money: Int, coins: List[Int]): Int = {
      if (money == 0)
      {
        1
      }
      else if (coins.isEmpty || money < 0)
      {
        0
      }
      else
      {
        countChange(money, coins.tail) + countChange(money - coins.head, coins)
      }
    }

    count(money, coins.sorted)
  }
}
