package com.example.eternity.cardvisitproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_information__register.*

class Information_Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information__register)

        back_bt.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

        register_bt.setOnClickListener({
            saveMyProfile()
            val intent = Intent(this, Update_CardvisitActivity::class.java)
            startActivity(intent)
        })
    }

    private fun saveMyProfile() {
        var preference  = getSharedPreferences("cardImage" , Context.MODE_PRIVATE)
        var editor = preference.edit()
        editor.putString("phone" , phone_number.text.toString())
        editor.commit()
    }
}
