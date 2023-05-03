package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.test.models.ExpenseModel
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class UpdateExpenses : AppCompatActivity() {

    private lateinit var updateExpCat : TextView
    private lateinit var updateExpAmt : TextView
    private lateinit var updateBtnExp : Button
    private lateinit var updateAmtInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_expenses)

        updateExpCat = findViewById(R.id.expenseUpCat)
        updateExpAmt = findViewById(R.id.expenseUpAmt)
        updateBtnExp = findViewById(R.id.updateExpenseBtn)
        updateAmtInput = findViewById(R.id.editTextNumber2)

        setValuesToExpUpdate()

        updateBtnExp.setOnClickListener(){
            updateExp(intent.getStringExtra("expId").toString(),intent.getStringExtra("expCat").toString(),updateAmtInput.text.toString())
        }
    }
    private fun setValuesToExpUpdate(){
        updateExpCat.text = intent.getStringExtra("expCat")
        updateExpAmt.text = intent.getStringExtra("expAmt")
        updateAmtInput.setText(intent.getStringExtra("expAmt"))
    }

    private fun updateExp(id:String, category:String, amt:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(id)
        val expInfo = ExpenseModel(id,amt,category)
        dbRef.setValue(expInfo)

        val Intent = Intent(this,ViewExpenses::class.java);
        startActivity(Intent);
    }
}