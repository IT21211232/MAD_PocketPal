package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.test.models.SavingsModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddSavings : AppCompatActivity() {

    private lateinit var etCategory:EditText
    private lateinit var etAmount:EditText
    private lateinit var btnAddSaving:Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_savings)

        etCategory=findViewById(R.id.addcategory)
        etAmount=findViewById(R.id.addamount)

        btnAddSaving=findViewById<Button>(R.id.addsavingbtn)

        dbRef=FirebaseDatabase.getInstance().getReference("Savings")

        btnAddSaving.setOnClickListener{
            AddemployeeData()
        }
    }
    private fun AddemployeeData(){
        val category=etCategory.text.toString()
        val amount=etAmount.text.toString()

        if (category.isEmpty()){
            etCategory.error="Required Field is empty"
        }

        if (amount.isEmpty()){
            etAmount.error="Required Field is empty"
        }

        val savingId= dbRef.push().key!!

        val save= SavingsModel(savingId,category,amount)

        dbRef.child(savingId).setValue(save)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted sucessfully",Toast.LENGTH_LONG).show()

                etCategory.text.clear()
                etAmount.text.clear()

            }.addOnFailureListener{
                err->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()

            }
    }
}