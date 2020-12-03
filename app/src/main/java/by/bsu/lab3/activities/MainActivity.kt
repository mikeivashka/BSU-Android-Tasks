package by.bsu.lab3.activities

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import by.bsu.lab3.ExitDialog
import by.bsu.lab3.R
import by.bsu.lab3.services.TimerService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_input.*

class MainActivity : AppCompatActivity() {
    var isTimerServiceBound: Boolean = false
    private val serviceConnection: ServiceConnection = object :
        ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            isTimerServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isTimerServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonOk.setOnClickListener {
            val okIntent = Intent(this, SecondActivity::class.java)
            val data = editText.text.toString()
            okIntent.putExtra("question", data)
            startActivityForResult(okIntent, 10)
        }
        editText.setHint(R.string.question_hint)
    }

    override fun onDestroy() {
        unbindService(serviceConnection)
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("result")
                textViewResult.text = result
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.close -> {
                val exitDialog = ExitDialog()
                exitDialog.show(this.supportFragmentManager, "dialog")
                super.onOptionsItemSelected(item)
            }
            R.id.startTimer -> {
                bindService(
                    Intent(this, TimerService::class.java),
                    serviceConnection,
                    Context.BIND_AUTO_CREATE
                )
                true
            }
            R.id.stopTimer -> {
                if (isTimerServiceBound) {
                    unbindService(serviceConnection)
                    isTimerServiceBound = false
                }
                return true
            }
            else -> {
                false
            }
        }


    }
}
