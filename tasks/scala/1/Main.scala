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
    if(c==r||c==0)
      1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    def recfun(chars: List[Char]): Int = {
      if (chars.isEmpty) 0
      val i = if (!chars.tail.isEmpty) recfun(chars.tail) else 0
      if (chars.head == '(') 1 + i
      else {
        if (chars.head == ')') -1 + i
        else i
      }
    }

    if(recfun(chars) == 0) true
    else false
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    coins.distinct
    if(money == 0) 1
    else {
      if (money > 0 && !coins.isEmpty)
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
      else 0
    }
  }
}