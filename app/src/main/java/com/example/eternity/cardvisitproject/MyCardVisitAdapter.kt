package com.example.eternity.cardvisitproject

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_list_row.view.*

class MyCardVisitAdapter(private val myDataset: Array<String>) :
    RecyclerView.Adapter<MyCardVisitAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val convertView: View) : RecyclerView.ViewHolder(convertView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyCardVisitAdapter.MyViewHolder {
        // create a new view
        val convertView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list_row, parent, false) as View
        // set the view's size, margins, paddings and layout parameters


        return MyViewHolder(convertView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.convertView.card_img.setImageResource(R.drawable.update)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size

    interface OnItemClickListener{
        fun onclick(username : String)
    }
}