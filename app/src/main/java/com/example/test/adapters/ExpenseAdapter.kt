package com.example.test.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.test.AddExpenses
import com.example.test.R
import com.example.test.UpdateExpenses
import com.example.test.models.ExpenseModel
import com.google.firebase.database.FirebaseDatabase

class ExpenseAdapter (private val expList: ArrayList<ExpenseModel>) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.expense_recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentExp = expList[position]
        holder.expCat.text = currentExp.expCat
        holder.expAmount.text = currentExp.expAmt
        holder.editBtn.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateExpenses::class.java);
            intent.putExtra("expId", currentExp.expId)
            intent.putExtra("expCat", currentExp.expCat)
            intent.putExtra("expAmt", currentExp.expAmt)
            holder.itemView.context.startActivity(intent);
        }
        holder.deleteBtn.setOnClickListener{
            fun deleteRecord(id:String){
                val dbRef = FirebaseDatabase.getInstance().getReference("Expenses").child(id)
                val mTask = dbRef.removeValue()
            }
            deleteRecord(currentExp.expId.toString())
        }
    }

    override fun getItemCount(): Int {
        return expList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val expCat : TextView = itemView.findViewById(R.id.expenseRecycler_catName)
        val expAmount : TextView = itemView.findViewById(R.id.recyclerAmount)
        val editBtn : Button = itemView.findViewById(R.id.editExpBtn)
        val deleteBtn : Button = itemView.findViewById(R.id.deleteExpBtn)
    }
}