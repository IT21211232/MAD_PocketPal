package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.test.models.ExpenseModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class AddExpenses : AppCompatActivity() {
    val expenseCategoriesArr = arrayOf("Food", "Clothing", "Car", "Transport", "Entertainment", "House", "Other");

    /*Variables for storing the values taken from an input*/
    private lateinit var etExpenseAmt : EditText
    private lateinit var addExpenseBtn : Button
    private lateinit var categoryTextDisplay : TextView
    /*Initializing the database reference*/
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expenses)

        /*Grabbing the elements to take the values*/
        etExpenseAmt = findViewById(R.id.editTextNumber)
        addExpenseBtn = findViewById(R.id.addExpenseBtn2)
        categoryTextDisplay = findViewById(R.id.expenseCategoryDisplay)
        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        val addExpense = findViewById<Button>(R.id.addExpenseBtn2);
        addExpense.setOnClickListener{
            /*
            val Intent = Intent(this,UpdateExpenses::class.java);
            startActivity(Intent);
             */
            saveExpense()
        }
        /*Coding for the spinner including the different categories of expenses*/

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
                categoryTextDisplay.text = expenseCategoriesArr[position]
                Toast.makeText(applicationContext,expenseCategoriesArr[position],Toast.LENGTH_SHORT).show();
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    private fun saveExpense(){
        //getting the values from the edittext
        val expAmt = etExpenseAmt.text.toString()
        val expCat = categoryTextDisplay.text.toString()

        if(expAmt.isEmpty()){
            etExpenseAmt.error="Please Enter the Amount"
        }
        val expenseId = dbRef.push().key!!

        val expense = ExpenseModel(expenseId, expAmt, expCat)

        dbRef.child(expenseId).setValue(expense)
            .addOnCompleteListener{
                Toast.makeText(this, "Expense Inserted Successfully!", Toast.LENGTH_LONG).show()
                etExpenseAmt.text.clear()
            }.addOnFailureListener{ err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}