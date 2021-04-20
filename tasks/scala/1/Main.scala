object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle:")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println()
    println("Parentheses Balancing:")
    val bracketsList: List[List[Char]] =
      List(
        List('(', '(', '(', ')', ')', ')'),
        List('(', '(', '(', ')', ')'),
        List('(', '(', '(', ')', ')', ')', ')'),
        List('(', '(', ')', '(', '(', ')', ')', ')')
      )
    for (i: Int <- bracketsList.indices) {
      if (balance(bracketsList.apply(i)))
        println("The " + i + " brackets is balanced")
      else
        println("The " + i + " brackets is unbalanced")
    }
    println()
    println("Counting Change:")
    val denominations: List[Int] = List(1, 2, 3)
    val combination: Int = countChange(6, denominations)
    println("There are " + combination + " combination(s).")
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
    def matchBracket(chars: List[Char], count: Int): Boolean = {
      if (count < 0) return false
      if (chars.isEmpty) count == 0
      else if (chars.head == '(') matchBracket(chars.tail, count + 1)
      else if (chars.head == ')') matchBracket(chars.tail, count - 1)
      else matchBracket(chars.tail, count)
    }

    matchBracket(chars, 0)
  }


  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    def recursion(money: Int, start: Int, coins: List[Int]): Int = {
      if (money == 0) return 1
      if (money < 0 || start == coins.length) return 0
      recursion(money, start + 1, coins) + recursion(money - coins.apply(start), start, coins)
    }

    if (money <= 0) return 0
    recursion(money, 0, coins.distinct.filter(_ > 0).sorted(Ordering[Int].reverse))
  }
}
