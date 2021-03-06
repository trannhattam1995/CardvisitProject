package com.example.eternity.cardvisitproject

//import android.support.v4.app.SupportActivity
//import android.support.v4.app.SupportActivity.ExtraData
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_update__cardvisit.*
import java.io.ByteArrayOutputStream

/*class KeyValuePair(key: String, value: String) : Pair<String, String>(key, value) {

    val key: String
        get() = super.first

    val value: String
        get() = super.second
}*/
class Update_CardvisitActivity : AppCompatActivity(),View.OnClickListener {

    var drawView : DrawView ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__cardvisit)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        drawView = findViewById(R.id.DrawView)
        circle_bt.setOnClickListener(this)
        quare_bt.setOnClickListener(this)
        line_bt.setOnClickListener(this)
        paint_bt.setOnClickListener(this)
        oval_bt.setOnClickListener(this)
        drag_bt.setOnClickListener(this)
        save_bt.setOnClickListener(this)
//        Fontsize_bt.setOnClickListener(){
//            Fontsize_bt.setVisibility(View.GONE)
//        }
//
//
//        /*移動させる処理*/
//        val moveLis : DragViewListener = DragViewListener(TextView(this))

        /*カラースピナー*/
        color_sp.onItemSelectedListener =
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
//                            var selected_textView = selected as TextView
//                            selected_textView.setTextColor(Color.parseColor(it))
                            drawView!!.setPaintColor(Color.parseColor(it))
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        /*フォント変更*/
        customfont_sp.onItemSelectedListener =
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
//                            var selected_textView = selected as TextView
//                            selected_textView.setTypeface(arrayOf(Typeface))
                              drawView!!.setPaintColor(Color.parseColor(it))
                              //drawView!!.paint.textSize = progress.toFloat()
                            }
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

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
        /*シークバーの処理*/
        fontsize_sb.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                    if( selected != null) {
//                        var selected_textView = selected as TextView
                        //drawView!!.size(TypedValue.COMPLEX_UNIT_SP,progress.toFloat())
                    drawView!!.paint.textSize = progress.toFloat()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )
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

            R.id.save_bt ->{
                val bitmap = Bitmap.createBitmap(DrawLayout.getWidth(), DrawLayout.getHeight(), Bitmap.Config.ARGB_8888)
                val canvas = Canvas(bitmap)
                DrawLayout.draw(canvas)

                var byteArrayOutputStream : ByteArrayOutputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG , 100, byteArrayOutputStream)
                var data = byteArrayOutputStream.toByteArray()
                val imageEncoded = Base64.encodeToString(data, Base64.DEFAULT)

                var preference  = getSharedPreferences("CARDVISIT" , Context.MODE_PRIVATE)
                var editor = preference.edit()
                editor.putString("FRONT_IMG" , imageEncoded)
                editor.commit()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}
