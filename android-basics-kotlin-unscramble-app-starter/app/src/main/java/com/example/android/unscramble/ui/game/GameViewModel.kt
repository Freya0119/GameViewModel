package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val _score = MutableLiveData<Int>(0)
    private val _currentWordCount = MutableLiveData<Int>(0)
    private val _currentScrambledWord = MutableLiveData<String>("test")

    val score: LiveData<Int> get() = _score
    val currentWordCount: LiveData<Int> get() = _currentWordCount
    val currentScrambledWord: LiveData<String> get() = _currentScrambledWord

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
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = _currentWordCount.value?.inc()
            wordsList.add(currentWord)
        }
    }

    //Seventh
    fun nextWord(): Boolean {
        return if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            Log.d("GameFragment", currentWord)
            true
        } else {
            false
        }
    }

    //Tenth
    private fun increaseScore() {
        _score.value = _score.value?.plus(SCORE_INCREASE)
    }

    fun isUserWordCorrect(playerWord: String): Boolean {
        return if (playerWord.equals(currentWord, true)) {
            increaseScore()
            true
        } else false
    }

    //Sixteenth
    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }
}