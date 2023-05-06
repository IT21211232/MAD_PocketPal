package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.test.models.ExpenseModel
import com.example.test.models.IncomeModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddIncome : AppCompatActivity() {

    /*variables to store inputs */
    private lateinit var incomeAmount: EditText
    private lateinit var incomeCategory: RadioGroup
    private lateinit var addIncomeBtn: Button
    private lateinit var backArr: ImageView

    /* connect database */
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income)


        incomeAmount = findViewById(R.id.addIncomeAmount)
        incomeCategory = findViewById(R.id.incomeRadioGrp)
        addIncomeBtn = findViewById(R.id.btnAddIncome2)
        dbRef = FirebaseDatabase.getInstance().getReference("Income")


        /* back arrow */
        backArr = findViewById(R.id.backArrow)
        backArr.setOnClickListener{
            val intent = Intent(this,ViewIncome::class.java);
            startActivity(intent);
        }

        /* add income button */
        addIncomeBtn.setOnClickListener {
            /* this takes the value of the selected radio button */
            val selectedRadioOptionId = incomeCategory.checkedRadioButtonId

            /* validates the radio grp - not empty */
            if (selectedRadioOptionId == -1) {
                Toast.makeText(this, "Please select a radio button", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioOption = findViewById<RadioButton>(selectedRadioOptionId)
            val selectedValue = selectedRadioOption.text.toString()

            /* toast messages */
            Toast.makeText(this, "$selectedValue is selected", Toast.LENGTH_SHORT).show()


            val inputAmount = incomeAmount.text.toString().trim()

            /* validates the amount - not empty */
            if (inputAmount.isEmpty()) {
                incomeAmount.error = "Amount not entered"
                return@setOnClickListener
            }
            if (inputAmount == "0") {
                incomeAmount.error = "Amount cannot be zero"
                return@setOnClickListener
            }

            /* store amount and category in firebase */
            val incomeId = dbRef.push().key!!

            val income = IncomeModel(incomeId, inputAmount, selectedValue)

            dbRef.child(incomeId).setValue(income)
                .addOnCompleteListener {
                    Toast.makeText(this, "Income Inserted Successfully!", Toast.LENGTH_LONG).show()
                    /* Clear the category selection and amount */
                    incomeCategory.clearCheck()
                    incomeAmount.text.clear()
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}