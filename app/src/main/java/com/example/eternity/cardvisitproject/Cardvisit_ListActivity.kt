package com.example.eternity.cardvisitproject

import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_cardvisit__list.*

class Cardvisit_ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var viewAdapter: androidx.recyclerview.widget.RecyclerView.Adapter<*>
    private lateinit var viewManager: androidx.recyclerview.widget.RecyclerView.LayoutManager

    ///camera activity REQUEST_CODE
    private val REQUEST_CODE_CAMERA_ACTIVITY = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardvisit__list)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ///カメラで名刺を追加する
        add_cardvisit_bt.setOnClickListener({
            var intent : Intent = Intent(this , CameraActivity::class.java )
            startActivityForResult(intent , REQUEST_CODE_CAMERA_ACTIVITY)
        })

        ///戻るボタン
        back_cardvisit_bt.setOnClickListener({
            finish()
        })

        var databaseHelper : DatabaseHelper = DatabaseHelper(applicationContext , "PROJECT_DATABASE" , null , 1 )
        var db : SQLiteDatabase = databaseHelper.readableDatabase
        var arrayList : ArrayList<CardVisit> = databaseHelper.GetAllCardvisit(db)

        viewManager =
            androidx.recyclerview.widget.LinearLayoutManager(this, RecyclerView.HORIZONTAL , false)

        viewAdapter = MyCardVisitAdapter(arrayList)

        recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.cardVisitRecylerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    ///activity から返す結果の処理
    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
        when(resultCode){
            REQUEST_CODE_CAMERA_ACTIVITY ->{

            }
        }
    }
}
