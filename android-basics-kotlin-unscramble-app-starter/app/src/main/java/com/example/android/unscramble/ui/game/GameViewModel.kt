package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels

class GameViewModel : ViewModel() {
    private var _score = 0
    var currentWordCount = 0
    private var _currentScrambledWord: String = "test"

    val currentScrambledWord get() = _currentScrambledWord
    val score get() = _score

    //Fifth
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    //Second
    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    //Third
    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }

    //Sixth
    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            currentWordCount += 1
            wordsList.add(currentWord)
        }
    }

    //Seventh
    fun nextWord(): Boolean {
        return if (currentWordCount < MAX_NO_OF_WORDS) {
            getNextWord()
            true
        } else false
    }

    //Tenth
    private fun increaseScore() {
        _score += SCORE_INCREASE
    }

    fun isUserCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(currentWord, true)) {
            increaseScore()
            true
        } else false
    }
}