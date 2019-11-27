package com.example.eternity.cardvisitproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cardvisit__list.*

class Cardvisit_ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardvisit__list)

        var list : ArrayList<CardList> = ArrayList()
        list.add(CardList())
        list.add(CardList())
        list.add(CardList())
        list.add(CardList())
        list.add(CardList())

        var adapter : CardListAdapter = CardListAdapter(this,R.layout.card_list_row , list)
        card_list.adapter = adapter
    }
}
