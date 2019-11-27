package com.example.eternity.cardvisitproject

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        update_bt.setOnClickListener({
            val intent = Intent(this, Update_CardvisitActivity::class.java)
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

        profile_update_bt.setOnClickListener({
            val intent = Intent(this ,Information_Register::class.java)
            startActivity(intent)
        })

        var preference  = getSharedPreferences("cardImage" , Context.MODE_PRIVATE)
        var img_decode : String = preference.getString("image" , "")
        var byte = Base64.decode( img_decode.toByteArray() , Base64.DEFAULT)
        card_visit_img.setImageBitmap( BitmapFactory.decodeByteArray(byte , 0 , byte.size))
    }


}
