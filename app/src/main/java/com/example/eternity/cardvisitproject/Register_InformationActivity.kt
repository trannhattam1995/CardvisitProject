package com.example.eternity.cardvisitproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register__information.*

class Register_InformationActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register__information)
//        画面を強制的に立てにする
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        保存したデータを画面に表示
        displayMyProfile()
//        情報を保存するボタンーをクリック
        save_information_bt.setOnClickListener({
            saveMyProfile()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
//        バックボタンーをクリック
        close_bt.setOnClickListener({
            finish()
        })
    }

    private fun displayMyProfile() {
        sharedPreferences = getSharedPreferences("CARDVISIT", Context.MODE_PRIVATE)
        var name = sharedPreferences.getString("NAME","")
        usename_text.setText(name)
        var phone_number = sharedPreferences.getString("PHONE_NUMBER","")
        phone_number_text.setText(phone_number)
        var address = sharedPreferences.getString("ADDRES","")
        adress_text.setText(address)
        var email = sharedPreferences.getString("EMAIL","")
        email_text.setText(email)
        var sns = sharedPreferences.getString("SNS","")
        sns_text.setText(sns)
        var company_name = sharedPreferences.getString("COMPANY_NAME","")
        company_name_text.setText(company_name)
        var position = sharedPreferences.getString("POSITION","")
        position_text.setText(position)
        var company_url = sharedPreferences.getString("COMPANY_URL","")
        company_url_text.setText(company_url)
    }

    private fun saveMyProfile() {
        sharedPreferences = getSharedPreferences("CARDVISIT", Context.MODE_PRIVATE)
        var usename  = usename_text.text.toString().trim()
        var phone_number = phone_number_text.text.toString().trim()
        var adress = adress_text.text.toString().trim()
        var email = email_text.text.toString().trim()
        var sns = sns_text.text.toString().trim()
        var company_name = company_name_text.text.toString().trim()
        var position = position_text.text.toString().trim()
        var company_url = company_url_text.text.toString().trim()

        val editor = sharedPreferences.edit()

        if( usename == "" || phone_number == ""||adress == ""||email == ""||sns == ""||company_name == ""||position == ""||company_url == ""){
            Toast.makeText(this,"全ての項目を入力してください", Toast.LENGTH_SHORT).show()
        }else{
            editor.putString("NAME", usename)
            editor.putString("PHONE_NUMBER", phone_number)
            editor.putString("ADDRES", adress)
            editor.putString("EMAIL", email)
            editor.putString("SNS", sns)
            editor.putString("COMPANY_NAME", company_name)
            editor.putString("POSITION", position)
            editor.putString("COMPANY_URL", company_url)
            editor.apply()
            editor.commit()
        }
    }
}
