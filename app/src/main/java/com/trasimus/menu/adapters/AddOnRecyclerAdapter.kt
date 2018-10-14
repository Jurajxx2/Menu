package com.trasimus.menu.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trasimus.menu.R
import com.trasimus.menu.objects.AddOn
import com.trasimus.menu.objects.Meal
import kotlinx.android.synthetic.main.meal_row.view.*

class AddOnRecyclerAdapter(private val addOnList: List<AddOn>) : RecyclerView.Adapter<AddOnRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Viewholder for add on row layout
        var title: TextView = view.findViewById(R.id.addOn)
        var size: TextView = view.findViewById(R.id.addOnSize)
        var price: TextView = view.findViewById(R.id.addOnPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_on_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Assigning values to views
        holder.title.text = addOnList[position].name
        holder.size.text = addOnList[position].size
        holder.price.text = addOnList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return addOnList.size
    }
}