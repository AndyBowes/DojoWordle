package org.dojo.wordle

import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class DictionaryTest{
    val dict = Dictionary()

    @Test
    fun testLoadWords(){
        val words = dict.words()
        assertEquals("Expected 5748 words", 5748, words.size)
    }

    @Test
    fun testLetterFrequency(){
        val frequency = dict.words().letterFrequencies()
        assertEquals("Check frequencies", 26, frequency.size)
    }

    @Test
    fun testBestGuess(){
        val words = dict.words()
        val frequency = words.letterFrequencies()
        val bestGuess = words.bestGuess(frequency)!!

        assertEquals("Best Guess", "arose", bestGuess)
    }
}