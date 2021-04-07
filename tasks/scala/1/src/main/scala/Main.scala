import scala.annotation.tailrec

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }

    var testList : List[Char] = List('(', '(', '(', 'a', 'b', ')', ')', ')');
    println(balance(testList));

    var coins : List[Int] = List(1, 2, 5, 10);
    var sums : List[Int] = List(4, 5, 10, 15, 100);

    for(sum <- sums)
    {
      println(countChange(sum, coins))
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
  	if ( (r < 0) || (c < 0) )
    {
      0;
    }
    else
    {
      if(r == 0)
        {
          if(c == 0)
            {
              1;
            }
          else
            {
              0;
            }
        }
      else
        {
          pascal(c, r - 1) + pascal(c - 1, r -1);
        }
    }
  }

  /**
   * Exercise 2 Parentheses Balancing
   */

  def balance(chars: List[Char]): Boolean = {
    def recursiveBalance(chars: List[Char], unclosed: Int): Boolean =
    {
      chars match
      {
        case '(' :: remainingPart => recursiveBalance(remainingPart, unclosed + 1);
        case ')' :: remainingPart =>
          {
            if(unclosed == 0)
              {
                false;
              }
            else
              {
                recursiveBalance(remainingPart, unclosed - 1);
              }
          }
        case _ :: remainingPart => recursiveBalance(remainingPart, unclosed);
        case Nil => unclosed == 0;
        case _ => false;
      }
    }

    recursiveBalance(chars, 0);
  }


  /**
   * Exercise 3 Counting Change
   * Write a recursive function that counts how many different ways you can make
   * change for an amount, given a list of coin denominations. For example,
   * there is 1 way to give change for 5 if you have coins with denomiation
   * 2 and 3: 2+3.
   */

  def countChange(money: Int, coins: List[Int]): Int = {
    def recursiveGetCount(money: Int, coins: List[Int]): Int =
    {
      if(money == 0)
      {
        1;
      }
      else {
        if (money < 0) {
          0;
        }
        else {
          coins match {
            case x :: Nil => if (money == 0) {
              1;
            }
            else {
              if( (money % x) == 0)
                {
                  1
                }
              else
              {
                0;
              }
            };
            case x :: rest => {
              recursiveGetCount(money - x, coins) + recursiveGetCount(money, rest);
            };
            case Nil => 0;
          }
        }
      }
    }

    if(money > 0)
      {
       var correctList : List[Int] = coins.distinct.filter(_ > 0);
        recursiveGetCount(money, correctList);
      }
    else
      {
        0;
      }

  }

}
