package com.example.test.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.UpdateExpenses
import com.example.test.UpdateIncome
import com.example.test.models.IncomeModel
import com.google.firebase.database.FirebaseDatabase

class IncomeAdapter (private val incomeList: ArrayList<IncomeModel>) :
    RecyclerView.Adapter<IncomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.income_recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentIncome = incomeList[position]
        holder.incomeCategory.text = currentIncome.incomeCat
        holder.incomeAmount.text = currentIncome.incomeAmt
        holder.btnUpdate.setOnClickListener{
            val intent = Intent(holder.itemView.context, UpdateIncome::class.java);
            intent.putExtra("incomeID", currentIncome.incomeID)
            intent.putExtra("incomeCat", currentIncome.incomeCat)
            intent.putExtra("incomeAmt", currentIncome.incomeAmt)
            holder.itemView.context.startActivity(intent);
        }
        holder.btnDelete.setOnClickListener{
            fun deleteRecord(id:String){
                val dbRef = FirebaseDatabase.getInstance().getReference("Income").child(id)
                val mTask = dbRef.removeValue()
            }
            deleteRecord(currentIncome.incomeID.toString())
        }
    }

    override fun getItemCount(): Int {
        return incomeList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incomeCategory : TextView = itemView.findViewById(R.id.incomeRecycler_catName)
        val incomeAmount : TextView = itemView.findViewById(R.id.recyclerAmount)
        val btnUpdate : ImageButton = itemView.findViewById(R.id.btnUpdate)
        val btnDelete : ImageButton = itemView.findViewById(R.id.btnDelete)
    }
}