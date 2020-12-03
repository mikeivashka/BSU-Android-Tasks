package by.bsu.lab3.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import by.bsu.lab3.R

class TimerService : Service() {

    private var startTime: Long = 0L

    override fun onBind(intent: Intent?): IBinder {
        return Binder()
    }

    override fun onCreate() {
        startTime = System.currentTimeMillis()
        val toast = Toast.makeText(
            applicationContext,
            (R.string.timer_started),
            Toast.LENGTH_LONG
        )
        toast.show()
    }

    override fun onDestroy() {
        val timePassed =
            String.format("%.2f", ((System.currentTimeMillis() - startTime) / 1000.0))
        val toast = Toast.makeText(
            applicationContext,
            (timePassed + " " + getString(R.string.timer_hint)),
            Toast.LENGTH_LONG
        )
        startTime = 0L
        toast.show()
    }

}