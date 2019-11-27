package com.example.eternity.cardvisitproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

public class CardListAdapter : BaseAdapter{
    var context : Context? = null
    var row_layout : Int? = null
    var list_card : ArrayList<CardList>? = null

    constructor(context: Context?, row_layout: Int?, list_card: ArrayList<CardList>?) : super() {
        this.context = context
        this.row_layout = row_layout
        this.list_card = list_card
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater : LayoutInflater = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view : View = inflater.inflate(row_layout!! , null)
        var card_img : ImageView = view.findViewById(R.id.card_img)
        card_img.setImageResource(R.drawable.back)
        return view
    }

    override fun getItem(position: Int): Any {
        return list_card!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list_card!!.count()
    }
}