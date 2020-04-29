package com.example.ad340app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.submitButton)
        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)

        submitButton.setOnClickListener {
            val enteredMsg = zipcodeEditText.text.toString()
            if (enteredMsg.length != 5) {
                Toast.makeText(this, R.string.error_output, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, enteredMsg, Toast.LENGTH_SHORT).show()
            }

            //val finalMsg = getString(R.string.sample_output, enteredMsg, 5)
            //Toast.makeText(this, finalMsg, Toast.LENGTH_SHORT).show()


        }


    }
}




