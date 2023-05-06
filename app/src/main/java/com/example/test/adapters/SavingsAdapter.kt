package com.example.test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.models.SavingsModel
import org.w3c.dom.Text

class SavingsAdapter (private val savingList:ArrayList<SavingsModel>
        ):RecyclerView.Adapter<SavingsAdapter.ViewHolder>(){

        private lateinit var mListener:onItemClickListener

        interface onItemClickListener{

                fun onItemClick(position:Int)
        }

        fun setOnItemClickListener(clickListener: onItemClickListener){
                mListener=clickListener
        }



        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): SavingsAdapter.ViewHolder {

                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_savings,parent,false)
                return ViewHolder(itemView,mListener)

        }

        override fun onBindViewHolder(holder: SavingsAdapter.ViewHolder, position: Int) {
                val currentSaving = savingList[position]
                holder.tvcategory.text=currentSaving.category
                holder.tvamount.text=currentSaving.amount
        }


        override fun getItemCount(): Int {
                return savingList.size
        }

        class ViewHolder(itemView:View,clickListener: onItemClickListener): RecyclerView.ViewHolder(itemView) {

                val tvcategory:TextView = itemView.findViewById(R.id.catName)
                val tvamount: TextView= itemView.findViewById(R.id.Amount)


                init {
                    itemView.setOnClickListener{
                            clickListener.onItemClick(adapterPosition)
                    }
                }

        }

}