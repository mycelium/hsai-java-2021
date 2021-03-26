import forcomp.Anagrams.Occurrences

import scala.annotation.tailrec

package object forcomp {
  val dictionaryPath = List("forcomp", "linuxwords.txt")

  def loadDictionary = {
    val wordstream = Option {
      getClass.getClassLoader.getResourceAsStream(dictionaryPath.mkString("/"))
    } orElse {
      common.resourceAsStreamFromSrc(dictionaryPath)
    } getOrElse {
      sys.error("Could not load word list, dictionary file not found")
    }
    try {
      val s = io.Source.fromInputStream(wordstream)
      s.getLines.toList
    } catch {
      case e: Exception =>
        println("Could not load word list: " + e)
        throw e
    } finally {
      wordstream.close()
    }
  }

  /**
   * Append two lists recursively.
   */
  def append[T](a: List[T], b: List[T]): List[T] = a match {
    case Nil => b
    case x :: rest => x :: append(rest, b)
  }

  /**
   * Creates an 1d list that consists of all element of given 2d list
   */
  def flatten[T](bigList: List[List[T]]): List[T] = {
    @tailrec
    def recursion(l: List[List[T]]): List[List[T]] = l match {
      case Nil => Nil
      case _ :: Nil => l
      case x :: y :: rest => recursion(append(x, y) :: rest)
    }
    recursion(bigList).last
  }

}
