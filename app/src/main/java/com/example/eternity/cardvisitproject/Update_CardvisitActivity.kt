package com.example.eternity.cardvisitproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_information__register.*
import kotlinx.android.synthetic.main.activity_update__cardvisit.*
import org.w3c.dom.Text

class Update_CardvisitActivity : AppCompatActivity() {

    var selected : View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update__cardvisit)

        /*移動させる処理*/
        val moveLis : DragViewListener = DragViewListener(Textview)
        Companyname.setOnTouchListener(moveLis)
        Username.setOnTouchListener(moveLis)
        /*移動させる処理 ここまで */


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
            if( selected != null){
                var selected_textView = selected as TextView
                selected_textView.setTextSize(80f)
            }
        })

        /*Companyname.setTextIsSelectable(boolean selectable){
            mTextIsSelectable = true;
        }*/


    }


    /* textview 移動する処理*/
    internal inner class DragViewListener(view: View) : View.OnTouchListener {
        // ドラッグ中に移動量を取得するための変数
        private var oldx: Int = 0
        private var oldy: Int = 0

        override fun onTouch(view: View, event: MotionEvent): Boolean {
            // タッチしている位置取得
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()

            when (event.action) {
                MotionEvent.ACTION_DOWN ->{
                    selected = view
                }
                MotionEvent.ACTION_MOVE -> {
                    // 今回イベントでのView移動先の位置
                    val left = view.getLeft() + (x - oldx)
                    val top = view.getTop() + (y - oldy)
                    // Viewを移動する
                    view.layout(left, top, left + view.getWidth(), top + view.getHeight())
                }
            }

            // 今回のタッチ位置を保持
            oldx = x
            oldy = y
            // イベント処理完了
            return true
        }
    }
}
