package org.dojo.wordle

import java.io.File

class Dictionary {
    fun resourceFile(name: String) = File(ClassLoader.getSystemResource(name).file)
    fun words() = resourceFile("words.txt").readLines()
}

fun List<String>.letterFrequencies() : Map<Char, Int> {
    val letters = 'a' .. 'z'
    val letterFrequency: List<Pair<Char, Int>> = letters.map{ letter -> Pair(letter, this.count { word -> word.contains(letter) })}
    return letterFrequency.associate { it }
}

fun String.score( letterFrequencies: Map<Char, Int>) : Int {
    return this.asSequence().distinct().fold(0 ){ acc, letter -> acc + letterFrequencies.get(letter)!!}
}

fun List<String>.bestGuess(letterFrequencies: Map<Char, Int>) = this.maxBy { it.score(letterFrequencies) }
