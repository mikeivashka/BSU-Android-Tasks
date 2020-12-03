package by.bsu.lab3.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.bsu.lab3.ExitDialog
import by.bsu.lab3.R
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.fragment_input.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textViewQuestion.text = intent.getStringExtra("question")
        buttonOk.setOnClickListener {
            val returnIntent = Intent()
            val result = editText.text.toString()
            returnIntent.putExtra("result", result)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

        }

        editText.setHint(R.string.answer_hint)
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
                Toast.makeText(applicationContext, R.string.timer_not_allowed, Toast.LENGTH_LONG)
                    .show()
                true
            }
            R.id.stopTimer -> {
                Toast.makeText(applicationContext, R.string.timer_not_allowed, Toast.LENGTH_LONG)
                    .show()
                true
            }
            else -> {
                false
            }
        }

    }
}

