package com.example.eternity.cardvisitproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

public class CardVisitListViewAdapter : BaseAdapter{
    var context : Context? = null
    var list_cardvisit : ArrayList<CardVisit>? = null

    constructor(context: Context?, list_cardvisit: ArrayList<CardVisit>?) : super() {
        this.context = context
        this.list_cardvisit = list_cardvisit
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater  = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var convertView : View = inflater.inflate(R.layout.custom_listview_cardvisit , null)
        var company = convertView.findViewById<TextView>(R.id.company_name)
        company.text = list_cardvisit!![position].company_name
        var name = convertView.findViewById<TextView>(R.id.name)
        name.text = list_cardvisit!![position].name
        var pos = convertView.findViewById<TextView>(R.id.position)
        pos.text = list_cardvisit!![position].position
        return convertView
    }

    override fun getItem(position: Int): Any {
        return list_cardvisit!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list_cardvisit!!.size
    }

    fun clear(){
        list_cardvisit!!.clear()
    }

    fun add(cardvisit: CardVisit) {
        list_cardvisit!!.add(cardvisit)
    }

}