package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test.models.SavingsModel
import com.google.firebase.database.FirebaseDatabase

class UpdateSavings : AppCompatActivity() {

    private lateinit var etCategory:TextView
    private lateinit var etAmount:TextView
    private lateinit var etSavingsId:TextView
    private lateinit var btnUpdate:Button
    private lateinit var btnDelete:Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_savings)

        etCategory=findViewById(R.id.editcategory)
        etAmount=findViewById(R.id.editamount)
        etSavingsId=findViewById(R.id.editid)
        btnUpdate=findViewById(R.id.updatebtn)
        btnDelete=findViewById(R.id.deleteBtn)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener{

            openUpdateDialog(

                intent.getStringExtra("savingId").toString(),
                intent.getStringExtra("savingcategory").toString(),
                intent.getStringExtra("savingamount").toString(),

                )
        }


        btnDelete.setOnClickListener{

            deleteRecord(
                intent.getStringExtra("savingId").toString()
            )
        }


    }

    private fun deleteRecord(
        savingId: String
    ){
        val dbRef =FirebaseDatabase.getInstance().getReference("Savings").child(savingId)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Saving Deleted",Toast.LENGTH_LONG).show()

            val intent = Intent(this,Savings::class.java)
            finish()
            startActivity(intent)

        }.addOnFailureListener{error->

            Toast.makeText(this, "Deleting err",Toast.LENGTH_LONG).show()

        }


    }


    private fun initView(){}

    private fun setValuesToViews(){

        etSavingsId.text= intent.getStringExtra("savingId")
        etCategory.text = intent.getStringExtra("savingcategory")
        etAmount.text = intent.getStringExtra("savingamount")


    }

    private fun openUpdateDialog(
        savingId: String,
        savingcategory:String,
        savingamount:String

        ){

        val mDialog =AlertDialog.Builder(this)
        val inflater= layoutInflater
        val mDialogView=inflater.inflate(R.layout.update_dialog,null)

        mDialog.setView(mDialogView)

        val tvCategory=mDialogView.findViewById<EditText>(R.id.editcategory2)
        val tvAmount=mDialogView.findViewById<EditText>(R.id.editamount2)
        val btnUpdate=mDialogView.findViewById<Button>(R.id.updatebutton2)


        tvCategory.setText(intent.getStringExtra("savingcategory").toString(),)
        tvAmount.setText(intent.getStringExtra("savingamount").toString(),)

        mDialog.setTitle("Updating $savingcategory Record")

        val alertDialog = mDialog.create()

        alertDialog.show()

        btnUpdate.setOnClickListener{

            updateSavingData(

                savingId,
                tvCategory.text.toString(),
                tvAmount.text.toString()
            )

            Toast.makeText(applicationContext,"Data Updated", Toast.LENGTH_LONG).show()

            etCategory.text=tvCategory.text.toString()
            etAmount.text=tvAmount.text.toString()


            alertDialog.dismiss()

        }


    }

    private fun updateSavingData(savingId: String) {

    }

    private fun updateSavingData(
        savingId: String,
        category: String,
        amount: String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Savings").child(savingId)
        val savInfo= SavingsModel(savingId,category,amount)
        dbRef.setValue(savInfo)
    }
}