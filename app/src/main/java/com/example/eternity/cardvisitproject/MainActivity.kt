package com.example.eternity.cardvisitproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        update_bt.setOnClickListener({
            val intent = Intent(this, Update_CardvisitActivity::class.java)
            startActivity(intent)
        })
    }


}
