package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.adapters.SavingsAdapter
import com.example.test.models.SavingsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Savings : AppCompatActivity() {

    /*storing the values from inputs*/

    private lateinit var btnInsertData:Button
    private lateinit var savingsRecyclerView:RecyclerView
    private lateinit var tvLoadingData:TextView
    private lateinit var tvSavingPrice:TextView
    private lateinit var savingsList:ArrayList<SavingsModel>
    private lateinit var dbRef:DatabaseReference



    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savings)

        //take elements for grab values

        btnInsertData=findViewById(R.id.addsavingbtn)
        savingsRecyclerView=findViewById(R.id.savingsRecyclerView)
        savingsRecyclerView.layoutManager=LinearLayoutManager(this)
        savingsRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoading)
        tvSavingPrice=findViewById(R.id.SavingPrice)


        savingsList= arrayListOf<SavingsModel>()

        getSavingData()

        btnInsertData.setOnClickListener{

            val intent = Intent(this,AddSavings::class.java)
            startActivity(intent)
        }

    }

    private fun getSavingData(){

        savingsRecyclerView.visibility= View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Savings")

        dbRef.addValueEventListener(object: ValueEventListener{

            override fun onDataChange(snapshot:DataSnapshot){
                savingsList.clear()
                if(snapshot.exists()){
                    for(savSnap in snapshot.children){
                        val saveData= savSnap.getValue(SavingsModel::class.java)
                        savingsList.add(saveData!!)
                    }

                    val mAdapter = SavingsAdapter(savingsList)
                    savingsRecyclerView.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object :SavingsAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent=Intent(this@Savings,UpdateSavings::class.java)

                            //

                            intent.putExtra("savingId",savingsList[position].savingId)
                            intent.putExtra("savingcategory",savingsList[position].category)
                            intent.putExtra("savingamount",savingsList[position].amount)
                            startActivity(intent)
                        }


                    })

                    savingsRecyclerView.visibility=View.VISIBLE
                    tvLoadingData.visibility=View.GONE
                }
            }
            override fun onCancelled(error:DatabaseError) {}

        })
    }
}