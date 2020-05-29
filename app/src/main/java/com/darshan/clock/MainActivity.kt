package com.darshan.clock

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var chronometer: Chronometer
    lateinit var playPauseBtn: ImageButton
    lateinit var resetBtn: ImageButton
    private var running: Boolean = false
    private var pauseOffset: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById(R.id.chronometer)
        playPauseBtn = findViewById(R.id.play_pause)
        resetBtn = findViewById(R.id.reset)

        playPauseBtn.setOnClickListener {
            if (running)
                stop()
            else
                start()
        }

        resetBtn.setOnClickListener { reset() }
    }

    private fun start() {
        running = true
        playPauseBtn.setImageResource(R.drawable.ic_pause_white_35dp)
        resetBtn.visibility = View.VISIBLE

        // start chronometer
        chronometer.base = (SystemClock.elapsedRealtime() - pauseOffset)
        chronometer.start()

    }

    private fun stop() {
        running = false
        playPauseBtn.setImageResource(R.drawable.ic_play_arrow_white_35dp)

        // stop chronometer
        chronometer.stop()
        pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
    }

    private fun reset() {
        running = false
        resetBtn.visibility = View.INVISIBLE
        playPauseBtn.setImageResource(R.drawable.ic_play_arrow_white_35dp)

        // reset chronometer
        chronometer.stop()
        chronometer.base = SystemClock.elapsedRealtime()
        pauseOffset = 0L
    }
}
