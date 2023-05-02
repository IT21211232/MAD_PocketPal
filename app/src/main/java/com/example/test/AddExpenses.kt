package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*


class AddExpenses : AppCompatActivity() {
    val expenseCategoriesArr = arrayOf("Food", "Clothing", "Car", "Transport", "Entertainment", "House", "Other");
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenses)

        val addExpense = findViewById<Button>(R.id.addExpenseBtn2);
        addExpense.setOnClickListener{

            val Intent = Intent(this,UpdateExpenses::class.java);
            startActivity(Intent);
        }

        val expenseCategorySpinner = findViewById<Spinner>(R.id.expenseCategorySelect);
        val arrayAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, expenseCategoriesArr)
        expenseCategorySpinner.adapter = arrayAdapter
        expenseCategorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(applicationContext,expenseCategoriesArr[position],Toast.LENGTH_SHORT).show();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}