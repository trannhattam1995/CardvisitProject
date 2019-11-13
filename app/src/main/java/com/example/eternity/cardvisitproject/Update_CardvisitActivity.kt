package com.example.eternity.cardvisitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_information__register.*
import kotlinx.android.synthetic.main.activity_update__cardvisit.*

class Update_CardvisitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__cardvisit)

        Color_sp.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override
                fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty()){
                            //Companyname.textColors = it
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        Fontsize_bt.setOnClickListener({
            Companyname.setTextSize(80f)
        })

        /*Companyname.setTextIsSelectable(boolean selectable){
            mTextIsSelectable = true;
        }*/


    }
}
