package com.example.grillayout.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.grillayout.R

class CatAgeAdapter(private val catAgeList: List<CatAge>, private val parentLifecycleOwner: LifecycleOwner) :
    RecyclerView.Adapter<CatAgeAdapter.CatHolder>() {

    class CatHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ageButton = itemView.findViewById<Button>(R.id.button_age)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cat_age_item, parent, false)
        return CatHolder(itemView)
    }

    override fun onBindViewHolder(holder: CatHolder, position: Int) {
        holder.ageButton.text = catAgeList[position].age.toString()
        holder.ageButton.setOnClickListener {
            catAgeList[position].onClick()
        }
        catAgeList[position].isSelected.observe(
            parentLifecycleOwner
        ) { isSelected ->
            holder.ageButton.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, if (isSelected) R.color.black else R.color.purple_500)
            )
        }
    }

    override fun getItemCount() = catAgeList.size

}