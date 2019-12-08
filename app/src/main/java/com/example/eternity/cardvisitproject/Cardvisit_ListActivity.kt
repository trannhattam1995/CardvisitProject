package com.example.eternity.cardvisitproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_cardvisit__list.*

class Cardvisit_ListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    ///camera activity REQUEST_CODE
    private val REQUEST_CODE_CAMERA_ACTIVITY = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardvisit__list)

        ///カメラで名刺を追加する
        add_cardvisit_bt.setOnClickListener({
            var intent : Intent = Intent(this , CameraActivity::class.java )
            startActivityForResult(intent , REQUEST_CODE_CAMERA_ACTIVITY)
        })

        //Database
//        var databaseHelper : DatabaseHelper = DatabaseHelper(applicationContext , "PROJECT_DATABASE" , null , 1 )
//        var db : SQLiteDatabase = databaseHelper.writableDatabase
//
//        var user : User = User("aaa" ,12345 ,"test","test","test","test","test")
//        databaseHelper.SavaUser(db  , user)
//
//        var arrayList : ArrayList<User> = ArrayList()
//        arrayList = databaseHelper.GetAllUser(db)
//        var testuser = arrayList.get(0)
//        Toast.makeText(this , testuser.name , Toast.LENGTH_SHORT).show()

        viewManager = LinearLayoutManager(this , LinearLayout.HORIZONTAL , false)
        var myDataset : Array<String> = Array<String>(10 ){"dadfa"}

        viewAdapter = MyCardVisitAdapter(myDataset)

        recyclerView = findViewById<RecyclerView>(R.id.cardVisitRecylerView).apply {
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
