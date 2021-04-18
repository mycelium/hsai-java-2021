package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    println("\nParentheses Balancing")
    println("Empty line: " + balance(List()))
    val l1: List[Char] = List('(', 'a', 'b', 'c', ')')
    val l2: List[Char] = List('(', '(', 'b', 'c', ')')
    val l3: List[Char] = List('(', '(', 'b', ')', ')')
    val l4: List[Char] = List(')', '(', ')', '(')
    val l5: List[Char] = List('a', 'b', 'c')
    val l6: List[Char] = List(')')
    println("Line \"(abc)\": " + balance(l1))
    println("Line \"((bc)\": " + balance(l2))
    println("Line \"((b))\": " + balance(l3))
    println("Line \")()(\": " + balance(l4))
    println("Line \"abc\": " + balance(l5))
    println("Line \")\": " + balance(l6))

    println("\nCounting Change")
    println("Money: 5  Coins: 1,1,0,-1,-5,-5,2,3,1 \nWays to give a change: " + countChange(5, List(1, 1, 0, -1, -5, -5, 2, 3, 1)))
    println("Money: 0  Coins: 1,2,3 \nWays to give a change: " + countChange(0, List(1, 2, 3)))
    println("Money: -7  Coins: 1,2,3 \nWays to give a change: " + countChange(-7, List(1, 2, 3)))
    println("Money: 5  Coins: 2,3 \nWays to give a change: " + countChange(5, List(2, 3)))
    println("Money: 20  Coins: 2,3,5 \nWays to give a change: " + countChange(20, List(2, 3, 5)))
    println("Money: 14  Coins: 2,3,5 \nWays to give a change: " + countChange(14, List(2, 3, 5)))
    println("Money: 5  Coins: empty \nWays to give a change: " + countChange(5, List()))
    println("Money: 0  Coins: empty \nWays to give a change: " + countChange(0, List()))
    println("Money: -13  Coins: empty \nWays to give a change: " + countChange(-13, List()))
    println("Money: 39  Coins: 5 \nWays to give a change: " + countChange(39, List(5)))

  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else if (c > r || c < 0 || r < 0) {
      0
    } else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2 Parentheses Balancing
   */
  def balance(chars: List[Char]): Boolean = {
    if (parenthesesCount(chars) == 0) {
      true
    } else {
      false
    }
  }

  def parenthesesCount(chars: List[Char]): Int = chars match {
    case Nil => 0
    case '(' :: rest => 1 + parenthesesCount(rest)
    case ')' :: rest => -1 + parenthesesCount(rest)
    case _ :: rest => 0 + parenthesesCount(rest)
  }

  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (money>0) {
      nextStep(money, coins)
    }else
      0
  }

  def nextStep(money: Int, coins: List[Int]): Int = {
    var res: Int = 0
    if (!coins.distinct.filter(_ > 0).isEmpty && money > 0) {
      res = nextStep(money - coins.distinct.filter(_ > 0).head, coins) + nextStep(money, coins.distinct.filter(_ > 0).tail)
    } else if (money == 0) {
      res = 1
    }
    res
  }

}