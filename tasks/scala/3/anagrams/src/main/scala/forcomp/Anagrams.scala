package forcomp

import scala.annotation.tailrec

object Anagrams {

  /** A word is simply a `String`. */
  type Word = String

  /** A sentence is a `List` of words. */
  type Sentence = List[Word]

  /** `Occurrences` is a `List` of pairs of characters and positive integers saying
   *  how often the character appears.
   *  This list is sorted alphabetically w.r.t. to the character in each pair.
   *  All characters in the occurrence list are lowercase.
   *  
   *  Any list of pairs of lowercase characters and their frequency which is not sorted
   *  is **not** an occurrence list.
   *  
   *  Note: If the frequency of some character is zero, then that character should not be
   *  in the list.
   */
  type Occurrences = List[(Char, Int)]

  /** The dictionary is simply a sequence of words.
   *  It is predefined and obtained as a sequence using the utility method `loadDictionary`.
   */
  val dictionary: List[Word] = loadDictionary

  /**
   * Merges two occurrences list adding frequencies of same characters.
   */
  def merge(oc1: Occurrences, oc2: Occurrences): Occurrences = {
    oc1 match {
      case Nil => oc2
      case (c1, i1) :: rest1 => oc2 match {
        case Nil => oc1
        case (c2, i2) :: rest2 => if (c1 == c2) (c2, i1 + i2) :: merge(rest1, rest2)
        else if (c1 < c2) (c1, i1) :: merge(rest1, oc2)
        else (c2, i2) :: merge(oc1, rest2)
      }
    }
  }

  /** Converts the word into its character occurence list.
   *  
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  def wordOccurrences(w: Word): Occurrences = {
    @tailrec
    def recursion(word: Word, occurrences: Occurrences): Occurrences =
      if (word.isEmpty) occurrences
      else recursion(word.substring(1),
        merge(List((word.charAt(0).toLower, 1)), occurrences))
    recursion(w, Nil)
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = {
    def recursion(sentence: Sentence, oc: Occurrences): Occurrences =
      sentence match {
        case Nil => oc
        case w :: rest => merge(wordOccurrences(w), recursion(rest, oc))
      }
    recursion(s, Nil)
  }

  /**
   * Recursive function for filling occurrences map.
   */
  @tailrec
  def formMap(old: Map[Occurrences, List[Word]], words: List[Word]): Map[Occurrences, List[Word]] = {
    words match {
      case Nil => old
      case w :: rest =>
        val oc: Occurrences = wordOccurrences(w)
        val newMap = if (old.contains(oc)) {
          val newSequence: List[Word] = w :: old.getOrElse(oc, Nil)
          old - oc + (oc -> newSequence)
        }
        else old + (oc -> List(w))
        formMap(newMap, rest)
    }
  }

  /** The `dictionaryByOccurrences` is a `Map` from different occurrences to a sequence of all
   *  the words that have that occurrence count.
   *  This map serves as an easy way to obtain all the anagrams of a word given its occurrence list.
   *  
   *  For example, the word "eat" has the following character occurrence list:
   *
   *     `List(('a', 1), ('e', 1), ('t', 1))`
   *
   *  Incidentally, so do the words "ate" and "tea".
   *
   *  This means that the `dictionaryByOccurrences` map will contain an entry:
   *
   *    List(('a', 1), ('e', 1), ('t', 1)) -> Seq("ate", "eat", "tea")
   *
   */
  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = formMap(Map(), dictionary)

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = dictionaryByOccurrences.getOrElse(wordOccurrences(word), List(word))

  /** Returns the list of all subsets of the occurrence list.
   *  This includes the occurrence itself, i.e. `List(('k', 1), ('o', 1))`
   *  is a subset of `List(('k', 1), ('o', 1))`.
   *  It also include the empty subset `List()`.
   * 
   *  Example: the subsets of the occurrence list `List(('a', 2), ('b', 2))` are:
   *
   *    List(
   *      List(),
   *      List(('a', 1)),
   *      List(('a', 2)),
   *      List(('b', 1)),
   *      List(('a', 1), ('b', 1)),
   *      List(('a', 2), ('b', 1)),
   *      List(('b', 2)),
   *      List(('a', 1), ('b', 2)),
   *      List(('a', 2), ('b', 2))
   *    )
   *
   *  Note that the order of the occurrence list subsets does not matter -- the subsets
   *  in the example above could have been displayed in some other order.
   */
  def combinations(occurrences: Occurrences): List[Occurrences] = {
    // building a tree of all combinations recursively
    //  head - subset, that was taken during branch
    //  tail - elements of list, that was not branched yet
    //  current - current combinations
    def recursion(head: Occurrences, tail: Occurrences, current: List[Occurrences]): List[Occurrences] = {
      tail match {
        case Nil => head.reverse :: current
        case (c, 1) :: rest => append(recursion(head, rest, current),
          recursion((c, 1) :: head, rest, current))
        case (c, i) :: rest => append(recursion(head,(c, i - 1) :: rest, current),
          recursion((c, i) :: head, rest, current))
      }
    }
    recursion(Nil, occurrences, Nil)
  }

  /** Subtracts occurrence list `y` from occurrence list `x`.
   * 
   *  The precondition is that the occurrence list `y` is a subset of
   *  the occurrence list `x` -- any character appearing in `y` must
   *  appear in `x`, and its frequency in `y` must be smaller or equal
   *  than its frequency in `x`.
   *
   *  Note: the resulting value is an occurrence - meaning it is sorted
   *  and has no zero-entries.
   */
  def subtract(x: Occurrences, y: Occurrences): Occurrences = x match {
    case Nil => Nil
    case (cx, ix) :: restX => y match {
      case Nil => x
      case (cy, iy) :: restY => if (cx == cy) {
        if (ix == iy) subtract(restX, restY)
        else (cx, ix - iy) :: subtract(restX, restY)
      } else if (cx < cy) (cx, ix) :: subtract(restX, y)
        else subtract(x, restY) // it seems that it cannot happen due to precondition...
    }
  }

  /** Returns a list of all anagram sentences of the given sentence.
   *  
   *  An anagram of a sentence is formed by taking the occurrences of all the characters of
   *  all the words in the sentence, and producing all possible combinations of words with those characters,
   *  such that the words have to be from the dictionary.
   *
   *  The number of words in the sentence and its anagrams does not have to correspond.
   *  For example, the sentence `List("I", "love", "you")` is an anagram of the sentence `List("You", "olive")`.
   *
   *  Also, two sentences with the same words but in a different order are considered two different anagrams.
   *  For example, sentences `List("You", "olive")` and `List("olive", "you")` are different anagrams of
   *  `List("I", "love", "you")`.
   *  
   *  Here is a full example of a sentence `List("Yes", "man")` and its anagrams for our dictionary:
   *
   *    List(
   *      List(en, as, my),
   *      List(en, my, as),
   *      List(man, yes),
   *      List(men, say),
   *      List(as, en, my),
   *      List(as, my, en),
   *      List(sane, my),
   *      List(Sean, my),
   *      List(my, en, as),
   *      List(my, as, en),
   *      List(my, sane),
   *      List(my, Sean),
   *      List(say, men),
   *      List(yes, man)
   *    )
   *
   *  The different sentences do not have to be output in the order shown above - any order is fine as long as
   *  all the anagrams are there. Every returned word has to exist in the dictionary.
   *  
   *  Note: in case that the words of the sentence are in the dictionary, then the sentence is the anagram of itself,
   *  so it has to be returned in this list.
   *
   *  Note: There is only one anagram of an empty sentence.
   */
  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    // Firstly build all possible sequences of Occurrences
    //  occurrence - frequencies of what have left from sentence
    //  current - currently collected sequence of Occurrences
    //  result - all sequences, that was collected yet
    def sentenceSkeletons(occurrence: Occurrences,
                          current: List[Occurrences],
                          result: List[List[Occurrences]]): List[List[Occurrences]] = {
      // All frequencies is 0 - that means we have successfully reached end of sentence
      if (occurrence == Nil) current :: result
      else {
        // Find combinations, that appears in dictionary
        val comb: List[Occurrences] = combinations(occurrence).
          filter(oc => dictionaryByOccurrences.contains(oc))
        if (comb == Nil) result
        else {
          // For each combination run function recursively with the rest of
          // sentence frequencies
          flatten(comb.map(
            c => sentenceSkeletons(subtract(occurrence, c), c :: current, result)))
        }
      }
    }
    // Find all possible sentences for sequence of occurrences
    def buildSentences(skeleton: List[Occurrences], head: Sentence): List[Sentence] = skeleton match {
      case Nil => List(head.reverse)
      case oc :: rest => flatten(dictionaryByOccurrences.getOrElse(oc, Nil)
        .map(word => buildSentences(rest, word :: head)))
    }
    val occurrences: Occurrences = sentenceOccurrences(sentence)
    flatten(
    sentenceSkeletons(occurrences, Nil, Nil)
        .map(sk => buildSentences(sk, Nil))
    )
  }

}
