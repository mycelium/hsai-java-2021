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
    if ((c > r) || (c < 0) || (r < 0)) {
      return 0
    }
    else {
      val numinator : Int = factorial(r)
      val denumenator : Int = factorial(c) * factorial(r - c)
      val binomCoef : Int = numinator / denumenator
      return binomCoef
    }
  }

  /**
   * Recursive Factorial Calculation
   */
  def factorial(n: Int): Int = {
    if (n == 0) {
      return 1
    }
    else {
      val recursionVal = n * factorial(n - 1)
      return recursionVal
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    val parenthesesStatus = parenthesesCheck(chars, 0)
    return (parenthesesStatus == 0)
  }

  /**
   * Recursive Parentheses Check
   */
  def parenthesesCheck(chars : List[Char], counter : Int) : Int = {
    if (counter < 0) {
      return counter;
    }

    if (chars.isEmpty {
      return counter
    }
    else {
      chars.head match {
        case '(' => return parenthesesCheck(chars.tail, counter + 1)
        case ')' => return parenthesesCheck(chars.tail, counter - 1)
        case  _  => return parenthesesCheck(chars.tail, counter)
      }
    }
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if ((money < 0) || !coins.forall(value => {value > 0})) {
      return 0
    }

    return recursiveCount(money, coins)
  }

  def recursiveCount(money : Int, coins: List[Int]) : Int = {
    if (money == 0) {
      return 1
    }
    else if (money < 0 || coins.isEmpty) {
      return 0
    }
    else {
      val currentCoin : Int = coins.head
      val depthCount : Int = recursiveCount(money - currentCoin, coins)
      val widthCount : Int = recursiveCount(money, coins.tail)
      return depthCount + widthCount
    }
  }
}
