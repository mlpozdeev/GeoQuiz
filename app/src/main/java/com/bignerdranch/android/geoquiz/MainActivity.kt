package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding
import kotlin.math.roundToInt

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex = 0
    private var correctAnswersCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            checkAnswer(userAnswer = true)
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(userAnswer = false)
        }

        binding.nextButton.setOnClickListener {
            nextClicked()
        }
        binding.nextButton.isClickable = false

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun nextClicked() {
        binding.nextButton.isClickable = false
        if (currentIndex == questionBank.size - 1) {
            showQuizResult()
            return
        }
        currentIndex = (currentIndex + 1) % questionBank.size
        updateQuestion()
        enableAnswerButtons()
    }

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            correctAnswersCount++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

        disableAnswerButtons()
        binding.nextButton.isClickable = true
    }

    private fun disableAnswerButtons() {
        binding.trueButton.isClickable = false
        binding.falseButton.isClickable = false
    }

    private fun enableAnswerButtons() {
        binding.trueButton.isClickable = true
        binding.falseButton.isClickable = true
    }

    private fun showQuizResult() {
        val quizResult = (correctAnswersCount * 1.0 / questionBank.size * 100).roundToInt()
        Toast.makeText(this, getString(R.string.user_score_message, quizResult), Toast.LENGTH_SHORT)
            .show()
    }
}
