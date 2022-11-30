package com.example.grillayout.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.grillayout.R

class CatAdapter(private val catList: List<Cat>): RecyclerView.Adapter<CatAdapter.CatHolder>() {

    class CatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById<TextView>(R.id.textView_name)
        val ageView = itemView.findViewById<TextView>(R.id.textView_age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_item, parent, false)
        return CatHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.nameView.text = "меня зовут ${catList[position].name}"
        holder.ageView.text = "мне ${catList[position].age} лет"
    }

    override fun getItemCount() = catList.size

}