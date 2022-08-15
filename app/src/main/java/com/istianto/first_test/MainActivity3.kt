package com.istianto.first_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.work.*
import com.istianto.first_test.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding

    companion object {
        const val KEY_COUNT_VALUE = "key_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("LIFECYCLE", "On Create diPaggil")

        binding.button.setOnClickListener {
            setOneTimeWorkRequest()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "on start dipanggil")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "on resume dipanggil")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "on pause dipanggil")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "on stop dipanggil")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "on destroy dipanggil")
    }

    private fun setOneTimeWorkRequest() {
        val workManager = WorkManager.getInstance(applicationContext)

        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val data = Data.Builder()
            .putInt(KEY_COUNT_VALUE, 125)
            .build()

        val uploadRequest = OneTimeWorkRequest.Builder(UploadWorker::class.java)
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        workManager.enqueue(uploadRequest)

        workManager.getWorkInfoByIdLiveData(uploadRequest.id)
            .observe(this, {
                binding.status.text = it.state.name

                if (it.state.isFinished) {
                    val data = it.outputData
                    val message = data.getString(UploadWorker.KEY_WORKER)
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
