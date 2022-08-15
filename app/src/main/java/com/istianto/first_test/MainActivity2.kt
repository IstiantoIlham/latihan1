package com.istianto.first_test

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.app.NotificationCompat
import com.istianto.first_test.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    private var notificationManager: NotificationManager? = null
    private val channel_id = "channel_1"

    private lateinit var binding: ActivityMain2Binding
    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel(channel_id, "countdown", "Notification when countdown end")

        binding.btnStart.setOnClickListener {
            countDownTimer.start()
        }
        binding.btnStop.setOnClickListener {
            countDownTimer.cancel()
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(p0: Long) {
                binding.timer.text = getString(R.string.time_reamining, p0 / 1000)
            }

            override fun onFinish() {
                displayNotification()
            }
        }

    }

    private fun displayNotification() {
        val notificationId = 45
        val notification = NotificationCompat.Builder(this, channel_id)
            .setContentTitle("Countdown Timer")
            .setContentText("Your Time End")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)
        }
    }
}