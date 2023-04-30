package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val register = findViewById<Button>(R.id.LogAccbutton);
        register.setOnClickListener{

            val Intent = Intent(this,Login::class.java);
            startActivity(Intent);
        }

        val accoutsignin = findViewById<Button>(R.id.registerbutton);
        accoutsignin.setOnClickListener{

            val Intent = Intent(this,Login::class.java);
            startActivity(Intent);
        }




    }
}