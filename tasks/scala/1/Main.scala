package recfun
import common._

import scala.annotation.tailrec

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
   if(c == 0 || c ==r)
     1
   else
     pascal(c - 1,r - 1) + pascal (c , r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    rec(chars, 0)
  }

  @tailrec
  def rec(chars: List[Char], state: Int): Boolean = {
    chars match {
      case Nil => state == 0
      case '(' :: (rest: List[Char]) => rec(rest, state + 1)
      case ')' :: (rest: List[Char]) =>
        if (state > 0)
          rec(rest, state - 1)
        else
          false
      case _ :: (rest: List[Char]) => rec(rest, state)
    }
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomination
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if(money == 0) 1
    else
      if (money > 0 && coins.nonEmpty)
        countChange(money - coins.head, coins) + countChange(money, coins.tail)
      else 0
  }
}
