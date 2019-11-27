package com.example.eternity.cardvisitproject

import android.app.AlertDialog
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_information__register.*
import kotlinx.android.synthetic.main.activity_update__cardvisit.*
import org.w3c.dom.Text
import android.graphics.Typeface
import android.widget.TextView
//import android.support.v4.app.SupportActivity
//import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class Update_CardvisitActivity : AppCompatActivity(),View.OnClickListener {

    var drawView : DrawView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__cardvisit)


        drawView = findViewById(R.id.DrawView)

        circle_bt.setOnClickListener(this)
        quare_bt.setOnClickListener(this)
        line_bt.setOnClickListener(this)
        paint_bt.setOnClickListener(this)
        oval_bt.setOnClickListener(this)
        drag_bt.setOnClickListener(this)

//        Fontsize_bt.setOnClickListener(){
//            Fontsize_bt.setVisibility(View.GONE)
//        }
//
//
//        /*移動させる処理*/
//        val moveLis : DragViewListener = DragViewListener(TextView(this))
//
//        /*カラースピナー*/
//        Color_sp.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override
//                fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    val spinner = parent as? Spinner
//                    val item = spinner?.selectedItem as? String
//                    item?.let {
//                        if (it.isNotEmpty()){
//                            var selected_textView = selected as TextView
//                            selected_textView.setTextColor(Color.parseColor(it))
//
//                        }
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            }
//
//        /*テンプレートスピナー*/
//        Temp_sp.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override
//                fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//
//                }
//
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            }
//
//
//        Fontsize_bt.setOnClickListener({
//
//        })
//
//        /*フォント変更*/
//        Customfont_sp.onItemSelectedListener =
//            object : AdapterView.OnItemSelectedListener {
//                override
//                fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//                    val spinner = parent as? Spinner
//                    val item = spinner?.selectedItem as? String
//                    item?.let {
//                        if (it.isNotEmpty()){
//                            var selected_textView = selected as TextView
//
////                            selected_textView.setTypeface(arrayOf(Typeface))
//                        }
//                    }
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {}
//            }
//
//
//        /*シークバーの処理*/
//        Fontsize_sb.setOnSeekBarChangeListener(
//            object : SeekBar.OnSeekBarChangeListener{
//
//                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                    if( selected != null) {
//                        var selected_textView = selected as TextView
//                        selected_textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,progress.toFloat())
//                    }
//                }
//
//                override fun onStartTrackingTouch(p0: SeekBar?) {
//                }
//
//                override fun onStopTrackingTouch(p0: SeekBar?) {
//                }
//            }
//        )
//
//        Fontimage_bt.setOnClickListener({
//            var newText = TextView(this)
//            newText.setTextSize(60f)
//            newText.setText("永嶋　ひなえ　Nagasima　ヒナエ")
//            CardLayout.addView((newText))
//            newText.setOnTouchListener((moveLis))
//        })

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){


            R.id.circle_bt -> {
                drawView!!.shape_ID = Shape_ID.SHAPE_CIRCLE
            }

            R.id.quare_bt ->{
                drawView!!.shape_ID = Shape_ID.SHAPE_QUARE
            }

            R.id.line_bt ->{
                drawView!!.shape_ID = Shape_ID.SHAPE_LINE
            }

            R.id.paint_bt ->{
                drawView!!.shape_ID = Shape_ID.SHAPE_PAINT
            }

            R.id.oval_bt ->{
                drawView!!.shape_ID = Shape_ID.SHAPE_OVAL
            }

            R.id.drag_bt ->{
                drawView!!.shape_ID = Shape_ID.NONE_SHAPE
            }


        }
    }
}
