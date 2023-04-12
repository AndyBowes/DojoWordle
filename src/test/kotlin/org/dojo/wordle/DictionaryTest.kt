package org.dojo.wordle

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DictionaryTest{
    val dict = Dictionary()

    @Test
    fun testLoadWords(){
        val words = dict.words()
        print("Expected 5748 words")
        assert(5748 == words.size)
    }

    @Test
    fun testLetterFrequency(){
        val frequency = dict.words().letterFrequencies()
        print("Check frequencies")
        assertEquals(26, frequency.size)
    }

    @Test
    fun testBestGuess(){
        val words = dict.words()
        val frequency = words.letterFrequencies()
        val bestGuess = words.bestGuess(frequency)

        assertEquals("arose", bestGuess, "Best Guess")
    }
}
