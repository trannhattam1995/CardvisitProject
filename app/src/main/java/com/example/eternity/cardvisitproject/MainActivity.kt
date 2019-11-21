package com.example.eternity.cardvisitproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_information__register.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        update_bt.setOnClickListener({
            val intent = Intent(this, Information_Register::class.java)
            startActivity(intent)
        })

        cardvisit_list_view_bt.setOnClickListener({
            val intent = Intent(this, Cardvisit_ListActivity::class.java)
            startActivity(intent)
        })

        cardvisit_change_bt.setOnClickListener({
            val intent = Intent(this, Card_ChangeActivity::class.java)
            startActivity(intent)
        })


    }


}
