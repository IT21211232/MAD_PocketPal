package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val login = findViewById<Button>(R.id.CreateAccbutton);
        login.setOnClickListener{

            val Intent = Intent(this,Register::class.java);
            startActivity(Intent);
        }

        val dashboard = findViewById<Button>(R.id.loginbutton3);
        dashboard.setOnClickListener{

            val Intent = Intent(this,Dashboard::class.java);
            startActivity(Intent);
        }

    }
}