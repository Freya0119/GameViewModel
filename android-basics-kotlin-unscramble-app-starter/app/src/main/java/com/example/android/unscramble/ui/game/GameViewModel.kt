package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.fragment.app.viewModels

class GameViewModel : ViewModel() {
    //Second
    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }

    var score = 0
    var currentWordCount = 0
    private var _currentScrambledWord: String = "test"

    val currentScrambledWord get() = _currentScrambledWord

    //Fifth
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

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

        Log.d("TEST", "currentWord: $currentWord, tempWord: ${String(tempWord)}")
        _currentScrambledWord = String(tempWord)
        wordsList.add(currentWord)
//        if (wordsList.contains(currentWord)) {
//            Log.d("TEST2", _currentScrambledWord)}

//        if (wordsList.contains(currentWord)) {
//            getNextWord()
//        } else {
//            _currentScrambledWord = String(tempWord)
//            currentWordCount += 1
//            wordsList.add(currentWord)
//        }
    }
}