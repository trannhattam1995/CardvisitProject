package com.example.eternity.cardvisitproject

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import kotlinx.android.synthetic.main.activity_information__register.*

class Information_Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information__register)
//
//        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
//
//        val name = sharedPreferences.getString("NAME","")
//        val company_name = sharedPreferences.getString("COMPANY_NAME","")
//        val position = sharedPreferences.getString("POSITION","")
//        val department = sharedPreferences.getString("DEPARTMENT","")
//        val phone_number = sharedPreferences.getInt("PHONE_NUNBER",0)
//        val email = sharedPreferences.getString("EMAIL","")
//        val fax_number = sharedPreferences.getInt("FAX_NUNBER",0)
//        val address = sharedPreferences.getString("ADDRES","")
//        val sns = sharedPreferences.getString("SNS","")
//        val memo = sharedPreferences.getString("SNS","")
//

        back_bt.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

//            val name = name.text.toString().trim()
//            val company_name = company_name.text.toString().trim()
//            val position = position.text.toString().trim()
//            val department = department.text.toString().trim()
//            val phone_number = Integer.parseInt(phone_number.text.toString().trim())
//            val email = email.text.toString().trim()
//            val fax_number = Integer.parseInt(fax_number.text.toString().trim())
//            val address = address.text.toString().trim()
//            val sns = sns.text.toString().trim()
//            val memo = memo.text.toString().trim()
//
//            val editor = sharedPreferences.edit()
//
//            editor.putString("NAME", name)
//            editor.putString("COMPANY_NAME", company_name)
//            editor.putString("POSITION", position)
//            editor.putString("DEPARTMENT", department)
//            editor.putInt("PHONE_NUNBER", phone_number)
//            editor.putString("EMAIL", email)
//            editor.putInt("FAX_NUNBER", fax_number)
//            editor.putString("ADDRES", address)
//            editor.putString("SNS", sns)
//            editor.putString("MEMO", memo)
//
//            editor.apply()
        })

        register_bt.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}
