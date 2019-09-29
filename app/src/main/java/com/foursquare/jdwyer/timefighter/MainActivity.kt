package com.foursquare.jdwyer.timefighter

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    internal var score = 0

    internal lateinit var tapMeButton: Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var gameTimeTextView: TextView

    internal var gameStarted = false

    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 6000
    internal val countDownInterval: Long = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // once setContentView is loaded, we can use the R file
        setContentView(R.layout.activity_main)
        tapMeButton = findViewById(R.id.tapMeButton)
        gameScoreTextView = findViewById(R.id.gameScoreTextView)
        gameTimeTextView = findViewById(R.id.TimeLeftTextView)

        gameScoreTextView.text = getString(R.string.yourScore, score)
        gameTimeTextView.text = getString(R.string.timeLeft, score)

        tapMeButton.setOnClickListener {_ ->
            incrementScore()
        }

    }

    private fun resetGame() {
        score = 0
        gameScoreTextView.text = getString(R.string.yourScore, score)


        val initialTimeLeft = initialCountDown / 1000
        gameTimeTextView.text = getString(R.string.timeLeft, initialTimeLeft)

        countDownTimer = object : CountDownTimer(initialCountDown, countDownInterval) {
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                gameTimeTextView.text = getString(R.string.timeLeft, timeLeft)
            }

            override fun onFinish() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        gameStarted = false
    }

    private fun incrementScore() {
        score += 1
        val newScore = getString(R.string.yourScore, score)
        gameScoreTextView.text = newScore
    }
}