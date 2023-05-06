package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.test.models.ExpenseModel
import com.example.test.models.IncomeModel
import com.google.firebase.database.FirebaseDatabase

class UpdateIncome : AppCompatActivity() {
    private lateinit var updateIncomeCat: TextView
    private lateinit var updateIncomeAmt: TextView
    private lateinit var updateIncomeBtn: Button
    private lateinit var updateAmount: EditText
    private lateinit var backArr: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_income)

        updateIncomeCat = findViewById(R.id.incomeCategory)
        updateIncomeAmt = findViewById(R.id.newIncomeAmount)
        updateIncomeBtn = findViewById(R.id.btnUpdateIncome)
        updateAmount = findViewById(R.id.editNewIncomeText)

        setValuesToIncomeUpdate()

        updateIncomeBtn.setOnClickListener(){
            updateIncome(intent.getStringExtra("incomeID").toString(),intent.getStringExtra("incomeCat").toString(),updateAmount.text.toString())
        }

        /* back arrow */
        backArr = findViewById(R.id.backArrow)
        backArr.setOnClickListener{
            val intent = Intent(this,ViewIncome::class.java);
            startActivity(intent);
        }
    }

    private fun setValuesToIncomeUpdate() {
        updateIncomeCat.text = intent.getStringExtra("incomeCat")
        updateIncomeAmt.text = intent.getStringExtra("incomeAmt")
        updateAmount.setText(intent.getStringExtra("incomeAmt"))
    }

    private fun updateIncome(id:String, category:String, amount:String){
        val dbRef = FirebaseDatabase.getInstance().getReference("Income").child(id)
        val incomeInfo = IncomeModel(id,amount,category)
        dbRef.setValue(incomeInfo)

        val intent = Intent(this,ViewIncome::class.java);
        startActivity(intent);
    }
}