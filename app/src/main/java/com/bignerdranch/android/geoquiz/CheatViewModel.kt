package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val ANSWER_TEXT_KEY = "ANSWER_TEXT_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    var answerText: Int?
        @StringRes
        get() = savedStateHandle[ANSWER_TEXT_KEY]
        set(value) = savedStateHandle.set(ANSWER_TEXT_KEY, value)
}
