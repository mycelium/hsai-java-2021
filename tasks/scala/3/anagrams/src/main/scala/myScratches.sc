import forcomp.Anagrams.{Occurrences, Word, combinations, sentenceAnagrams, subtract, wordAnagrams, wordOccurrences}

wordOccurrences("")

wordAnagrams("")
wordAnagrams("Hello")

sentenceAnagrams(List("yes", "man")).foreach(println)
sentenceAnagrams(List(""))