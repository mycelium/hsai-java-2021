package forcomp

import sun.jvm.hotspot.memory.Dictionary

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

  /** Converts the word into its character occurence list.
   *  
   *  Note: the uppercase and lowercase version of the character are treated as the
   *  same character, and are represented as a lowercase character in the occurrence list.
   */
  //noinspection RemoveRedundantReturn
  def wordOccurrences(w: Word): Occurrences = {
    var occurrences : Occurrences = Nil
    val word : Word = w.toLowerCase
    val distinctLetters : List[Char] = word.toList.distinct

    for (c : Char <- distinctLetters) {
      val charCount = word.count(_ == c)
      val newOccurrence : (Char, Int) = (c, charCount)
      occurrences = newOccurrence :: occurrences
    }

    return occurrences.sortWith((x, y) => x._1 < y._1)
  }

  /** Converts a sentence into its character occurrence list. */
  def sentenceOccurrences(s: Sentence): Occurrences = wordOccurrences(s.mkString)

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
  def fillDictionary(dictionary: List[Word]) : Map[Occurrences, List[Word]] = {
    var dictionaryMap = Map.empty[Occurrences, List[Word]]

    for (w <- dictionary) {
      val occurrences : Occurrences = wordOccurrences(w)
      val mapEntry = dictionaryMap.get(occurrences)

      dictionaryMap = if (mapEntry.isEmpty) {
        dictionaryMap + (occurrences -> List(w))
      }
      else {
        dictionaryMap + (occurrences -> (w :: mapEntry.get))
      }
    }

    return dictionaryMap
  }

  lazy val dictionaryByOccurrences: Map[Occurrences, List[Word]] = fillDictionary(dictionary)

  /** Returns all the anagrams of a given word. */
  def wordAnagrams(word: Word): List[Word] = {
    val anagrams = dictionaryByOccurrences.get(wordOccurrences(word))
    if (anagrams.isEmpty) {
      return Nil
    }
    else {
      return anagrams.get
    }
  }

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
  def combRecursion(occurrences : Occurrences) : List[Occurrences] = {
    var currentOccurrences : List[Occurrences] = Nil

    for (o <- occurrences) {
      val modifiedOccurrences = if (o._2 == 1) {
        occurrences.filterNot(x => x._1 == o._1)
      }
      else {
        occurrences.map(x => if (x._1 == o._1) (x._1, x._2 - 1) else x)
      }

      if (!modifiedOccurrences.isEmpty) {
        currentOccurrences = modifiedOccurrences :: currentOccurrences ::: combRecursion(modifiedOccurrences)
      }
    }

    return currentOccurrences
  }

  def combinations(occurrences: Occurrences): List[Occurrences] = {
    Nil :: occurrences :: combRecursion(occurrences).distinct
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
  def subtract(x: Occurrences, y: Occurrences): Occurrences = {
    if (y.isEmpty) {
      return x
    }
    else {
      var subOccurrences : Occurrences = Nil

      for (ocX <- x) {
        val twinOccurrence = y.find(ocY => ocY._1 == ocX._1)
        if (twinOccurrence.isEmpty) {
          subOccurrences = ocX :: subOccurrences
        }
        else if (ocX._2 > twinOccurrence.get._2){
          subOccurrences = (ocX._1, ocX._2 - twinOccurrence.get._2) :: subOccurrences
        }
      }
      return subOccurrences.sortWith((x, y) => x._1 < y._1)
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
   *      List(en, as, my), List(en, as, my)
   *      List(en, my, as), List(en, my, as)
   *      List(man, yes), List(man, yes)
   *      List(men, say), List(men, say)
   *      List(as, en, my), List(as, en, my)
   *      List(as, my, en), List(as, my, en)
   *      List(sane, my),
   *      List(Sean, my), List(Sean, my)
   *      List(my, en, as), List(my, en, as)
   *      List(my, as, en), List(my, as, en)
   *      List(my, sane),
   *      List(my, Sean), List(my, Sean)
   *      List(say, men), List(say, men)
   *      List(yes, man) List(yes, man)
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
  def sentenceFromOccurrences(sentOccur : List[Occurrences],
                              curSentence : Sentence,
                              sentList : List[Sentence]) : List[Sentence] =
  {
    if (sentOccur.isEmpty) {
      return curSentence :: sentList
    }
    else {
      var curSentList : List[Sentence] = Nil

      for (o <- sentOccur) {
        val wordFromOccur = dictionaryByOccurrences(o)
        val restSentOccur = sentOccur.filterNot(oc => o == oc)

        for (w <- wordFromOccur) {
          curSentList = curSentList ::: sentenceFromOccurrences(restSentOccur, w :: curSentence, sentList)
        }
      }

      return curSentList ::: sentList
    }
  }

  def sentenceRecursion(sentenceOccur: Occurrences,
                        curSentAnagram : List[Occurrences],
                        sentAnagrams : List[Sentence]) : List[Sentence] =
  {
    if (sentenceOccur.isEmpty) {
      return sentenceFromOccurrences(curSentAnagram, Nil, Nil) ::: sentAnagrams
    }
    else {
      val sentComb : List[Occurrences] = combinations(sentenceOccur).filter(o => dictionaryByOccurrences.contains(o))
      var curSentAnagrams : List[Sentence] = Nil

      if (!sentComb.isEmpty) {
        for (o <- sentComb) {
          val restSentOccur = subtract(sentenceOccur, o)
          curSentAnagrams = sentenceRecursion(restSentOccur, o :: curSentAnagram, sentAnagrams) ::: curSentAnagrams
        }
      }

      return curSentAnagrams
    }
  }

  def sentenceAnagrams(sentence: Sentence): List[Sentence] = {
    val sentOccurrences : Occurrences = sentenceOccurrences(sentence)
    return sentenceRecursion(sentOccurrences, Nil, Nil).distinct
  }
}
