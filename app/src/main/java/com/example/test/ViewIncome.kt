package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.adapters.IncomeAdapter
import com.example.test.models.IncomeModel
import com.google.firebase.database.*

class ViewIncome : AppCompatActivity() {

    private lateinit var incomeRecyclerView : RecyclerView
    private lateinit var incomeLoad : ProgressBar
    private lateinit var incomeList: ArrayList<IncomeModel>
    private lateinit var dbRef: DatabaseReference
    private lateinit var backArr: ImageView
    private lateinit var incomeHead: TextView
    private var totalIncome: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_income)

        /* initializing the recyclerview and progress bar */
        incomeRecyclerView = findViewById(R.id.incomeRecView)
        incomeRecyclerView.layoutManager = LinearLayoutManager(this)
        incomeRecyclerView.setHasFixedSize(true)
        incomeLoad = findViewById(R.id.incomeLoading)
        incomeHead = findViewById(R.id.incomeAmount)

        incomeList = arrayListOf<IncomeModel>()

        getIncomeData()

        val addIncome = findViewById<Button>(R.id.btnAddIncome);
        addIncome.setOnClickListener{

            val intent = Intent(this,AddIncome::class.java);
            startActivity(intent);
        }

        /* back arrow */
        backArr = findViewById(R.id.backArrow)
        backArr.setOnClickListener{
            val intent = Intent(this,Dashboard::class.java);
            startActivity(intent);
        }
    }

    private fun getIncomeData() {
        incomeRecyclerView.visibility = View.GONE
        incomeLoad.visibility = View.VISIBLE

        /* referencing the database */
        dbRef = FirebaseDatabase.getInstance().getReference("Income")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                incomeList.clear()
                totalIncome = 0.0
                if (snapshot.exists()) {
                    for (incomeSnap in snapshot.children) {
                        val incomeData = incomeSnap.getValue(IncomeModel::class.java)
                        incomeList.add(incomeData!!)
                        totalIncome += incomeData.incomeAmt?.toDouble() ?: 0.0
                    }
                    val iAdapter = IncomeAdapter(incomeList)
                    incomeRecyclerView.adapter = iAdapter

                    incomeRecyclerView.visibility = View.VISIBLE
                    incomeLoad.visibility = View.GONE
                    incomeHead.text = "$totalIncome"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}