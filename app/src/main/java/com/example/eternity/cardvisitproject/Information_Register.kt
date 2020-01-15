package com.example.eternity.cardvisitproject

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_information__register.*

class Information_Register : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information__register)

        sharedPreferences = getSharedPreferences("CARDVISIT", Context.MODE_PRIVATE)

        var name_d = sharedPreferences.getString("NAME","")
        name.setText(name_d)
        var company_name_d = sharedPreferences.getString("COMPANY_NAME","")
        company_name.setText(company_name_d)
        var position_d = sharedPreferences.getString("POSITION","")
        position.setText(position_d)
        var department_d = sharedPreferences.getString("DEPARTMENT","")
        department.setText(department_d)
        var phone_number_d = sharedPreferences.getString("PHONE_NUMBER","")
        phone_number.setText(phone_number_d)
        var email_d = sharedPreferences.getString("EMAIL","")
        email.setText(email_d)
        var fax_number_d = sharedPreferences.getString("FAX_NUNBER","")
        fax_number.setText(fax_number_d)
        var address_d = sharedPreferences.getString("ADDRES","")
        address.setText(address_d)
        var sns_d = sharedPreferences.getString("SNS","")
        sns.setText(sns_d)
        var memo_d = sharedPreferences.getString("MEMO","")
        memo.setText(memo_d)

        back_bt.setOnClickListener({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            name_d = name.text.toString().trim()
            company_name_d = company_name.text.toString().trim()
            position_d = position.text.toString().trim()
            department_d = department.text.toString().trim()
            phone_number_d = phone_number.text.toString().trim()
            email_d = email.text.toString().trim()
            fax_number_d = fax_number.text.toString().trim()
            address_d = address.text.toString().trim()
            sns_d = sns.text.toString().trim()
            memo_d = memo.text.toString().trim()

            val editor = sharedPreferences.edit()

            editor.putString("NAME", name_d)
            editor.putString("COMPANY_NAME", company_name_d)
            editor.putString("POSITION", position_d)
            editor.putString("DEPARTMENT", department_d)
            editor.putString("PHONE_NUMBER", phone_number_d)
            editor.putString("EMAIL", email_d)
            editor.putString("FAX_NUNBER", fax_number_d)
            editor.putString("ADDRES", address_d)
            editor.putString("SNS", sns_d)
            editor.putString("MEMO", memo_d)

            editor.apply()
        })

        register_bt.setOnClickListener({
            saveMyProfile()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }

    private fun saveMyProfile() {

    }
}
