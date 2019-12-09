package com.example.eternity.cardvisitproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register_new_card_visit.*

class RegisterNewCardVisitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_card_visit)

        var picture_array = intent.getByteArrayExtra("picture")
        var picture_bitmap : Bitmap = BitmapFactory.decodeByteArray(picture_array , 0 , picture_array.size)
        picture_view.setImageBitmap(picture_bitmap)
    }
}
