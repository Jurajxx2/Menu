package com.trasimus.menu.adapters

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.trasimus.menu.R
import com.trasimus.menu.objects.AddOn
import com.hendraanggrian.widget.ExpandableItem
import com.hendraanggrian.widget.ExpandableRecyclerView


class AddOnCategoryRecycleAdapter(private val addOnList: ArrayList<AddOn>, private val context: Context, private val layoutManager: LinearLayoutManager) : ExpandableRecyclerView.Adapter<AddOnCategoryRecycleAdapter.MyViewHolder>(layoutManager) {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //Viewholder for addon category row layout - expendable item is main holder
        private val item: ExpandableItem = view.findViewById(R.id.addOnRow) as ExpandableItem
        var title: TextView = item.headerLayout.findViewById(R.id.addOnCategory)
        var addOns: RecyclerView = item.contentLayout.findViewById(R.id.addOnList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.addon_category_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        //Arraylist initialisation
        val categoryAddOns: ArrayList<AddOn> = arrayListOf()

        //Get addons with unique categories
        var addOn = AddOn()
        var category = ""
        var order = 0
        addOnList.forEach {
            if (it.type != category){
                category = it.type
                if (order == position){
                    addOn = it
                }
                order++
            }

            if (addOn.type == it.type){
                categoryAddOns.add(it)
            }
        }

        //Assign addon type to view
        holder.title.text = addOn.type

        //Adjust height by content
        holder.addOns.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT

        //Assign adapter recycler view
        val mAdapter = AddOnRecyclerAdapter(categoryAddOns)
        val mLayoutManager = LinearLayoutManager(context)
        holder.addOns.layoutManager = mLayoutManager
        holder.addOns.itemAnimator = DefaultItemAnimator()
        holder.addOns.adapter = mAdapter
    }

    override fun getItemCount(): Int {
        //Get addons with unique types
        var category = ""
        var size = 0
        addOnList.forEach {
            if (it.type != category){
                category = it.type
                size++
            }
        }
        return size
    }
}
