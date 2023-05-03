package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.adapters.ExpenseAdapter
import com.example.test.models.ExpenseModel
import com.google.firebase.database.*

class ViewExpenses : AppCompatActivity() {

    private lateinit var expRecyclerView : RecyclerView
    private lateinit var expensesLoad : ProgressBar
    private lateinit var expList: ArrayList<ExpenseModel>
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_expenses)

        expRecyclerView = findViewById(R.id.expenseRecyclerDisplay)
        expensesLoad = findViewById(R.id.expensesLoading)
        expRecyclerView.layoutManager = LinearLayoutManager(this)
        expRecyclerView.setHasFixedSize(true)

        expList = arrayListOf<ExpenseModel>()

        getExpenseData()

        val addExpense = findViewById<Button>(R.id.addExpenseBtn);
        addExpense.setOnClickListener{

            val Intent = Intent(this,AddExpenses::class.java);
            startActivity(Intent);
        }
    }
    private fun getExpenseData(){
        expRecyclerView.visibility = View.GONE
        expensesLoad.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Expenses")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                expList.clear()
                if(snapshot.exists()){
                    for(expSnap in snapshot.children){
                        val expData = expSnap.getValue(ExpenseModel::class.java)
                        expList.add(expData!!)
                    }
                    val mAdapter = ExpenseAdapter(expList)
                    expRecyclerView.adapter = mAdapter

                    expRecyclerView.visibility = View.VISIBLE
                    expensesLoad.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}