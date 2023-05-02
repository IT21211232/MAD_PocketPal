package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ViewExpenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)

        val addExpense = findViewById<Button>(R.id.addExpenseBtn);
        addExpense.setOnClickListener{

            val Intent = Intent(this,AddExpenses::class.java);
            startActivity(Intent);
        }
    }
}