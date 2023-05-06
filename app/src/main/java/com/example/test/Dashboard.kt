package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val viewExpense = findViewById<Button>(R.id.ViewExpenses);
        viewExpense.setOnClickListener{

            val Intent = Intent(this,ViewExpenses::class.java);
            startActivity(Intent);
        }

        val viewSavings = findViewById<Button>(R.id.ViewSavings);
        viewSavings.setOnClickListener{

            val Intent = Intent(this,Savings::class.java);
            startActivity(Intent);
        }

        val viewIncome = findViewById<Button>(R.id.ViewIncome);
        viewIncome.setOnClickListener{

            val intent = Intent(this,ViewIncome::class.java);
            startActivity(intent);
        }
    }
}