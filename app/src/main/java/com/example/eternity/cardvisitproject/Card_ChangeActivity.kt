package com.example.eternity.cardvisitproject

import android.app.Dialog
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import io.socket.client.IO
import kotlinx.android.synthetic.main.activity_card__change.*
import org.json.JSONArray
import org.json.JSONObject


class Card_ChangeActivity : AppCompatActivity() {

    var socket : io.socket.client.Socket? = null
    //var URL = "http://192.168.0.13:3000"
    var URL = "http://172.30.23.27:3000"

    var selected_user : String? = null
    ///自分の名刺
    var myCardVisit : CardVisit? = null

    ///近くに居るユーザ情報リスト
    var list_cardvist : ArrayList<CardVisit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card__change)

//        Log.d("GPS", "change display")
//        // 位置情報使用時に準備
//        if ((PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION  )) and
//            (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)))
//        {
//            // 位置情報を管理している LocationManager のインスタンスを生成
//            var locationManager: LocationManager? = getSystemService(LOCATION_SERVICE) as LocationManager
//            var locationProvider : String = ""
//
//            if (null !== locationManager) {
//                // GPSが利用可能になっているかどうかをチェック
//                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    locationProvider = LocationManager.GPS_PROVIDER
//                } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//                    locationProvider = LocationManager.NETWORK_PROVIDER
//                } else {
//                    // いずれも利用可能でない場合は、GPSを設定する画面に
//                    val settingsIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
//                    startActivity(settingsIntent)
//                    return
//                }
//
//                // 変化検出時に呼ばれる
//                locationManager.requestLocationUpdates(
//                    locationProvider,
//                    500, // 通知のための最小時間間隔（ミリ秒）
//                    1.0F, // 通知のための最小距離間隔（メートル）
//                    object : LocationListener {
//                        override fun onLocationChanged(location: Location) {
//                            // ここで座標をもとめている
//                            val lat = location.latitude
//                            val lng = location.longitude
//                            Log.d("GPS", lat.toString() + " " + lng.toString())
//
//
//                        }
//                        override fun onProviderDisabled(provider: String) {}
//                        override fun onProviderEnabled(provider: String) {}
//                        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
//                    }
//                )
//            }
//        }




    }

    override fun onResume() {
        super.onResume()
        /*** SharePreference から情報を取得 ***/
        var preference  = getSharedPreferences("CARDVISIT" , Context.MODE_PRIVATE)
        var front_img : String = preference.getString("FRONT_IMG" , "")
        var back_img : String = preference.getString("BACK_IMG" , "")
        var front_img_tobyte = Base64.decode( front_img.toByteArray() , Base64.DEFAULT)
        var id : String = preference.getString("ID","1")
        var name : String = preference.getString("NAME" , "")
        var phone_number : String = preference.getString("PHONE_NUMBER" , "0")
        var address : String = preference.getString("ADRESS" , "")
        var email : String = preference.getString("EMAIL" , "")
        var sns : String = preference.getString("SNS" , "")
        var company_name : String = preference.getString("COMPNAY_NAME" , "")
        var position : String = preference.getString("POSITION" , "")
        var company_url : String = preference.getString("COMPANY_URL" , "")

        /// 自分の名刺インスタンスを生成
        myCardVisit = CardVisit(front_img , back_img ,name , phone_number.toInt() , address ,email , company_name , position , company_url )

        // 名刺を交換する処理
        var users : ArrayList<String> = ArrayList()

        //サーバーにアクセス
        socket = IO.socket(URL)
        socket!!.connect()

        /// 自分の名刺オブジェクトをJsonオブジェクトに変換
        var myCardVisitJson : JSONObject = JSONObject()
        myCardVisitJson.put("FRONT_IMG", myCardVisit!!.front_img)
        myCardVisitJson.put("BACK_IMG", myCardVisit!!.back_img)
        myCardVisitJson.put("ID", myCardVisit!!.id)
        myCardVisitJson.put("NAME", myCardVisit!!.name)
        myCardVisitJson.put("PHONE_NUMBER", myCardVisit!!.phone_number)
        myCardVisitJson.put("ADDRESS", myCardVisit!!.address)
        myCardVisitJson.put("EMAIL", myCardVisit!!.email)
        myCardVisitJson.put("COMPANY_NAME", myCardVisit!!.company_name)
        myCardVisitJson.put("POSITION", myCardVisit!!.position)
        myCardVisitJson.put("COMPANY_URL", myCardVisit!!.company_url)
        //リクエスト時の時刻とGPS情報
        myCardVisitJson.put("REQUEST_TIME", 0)
        myCardVisitJson.put("GPS", 0)
        ///サーバに近くに居るユーザを検索イベント
        socket!!.emit("FIND_USER" , myCardVisitJson)

        // 近くに居るユーザ一覧のアダプタ
        list_cardvist = ArrayList()
        var adapter : CardVisitListViewAdapter = CardVisitListViewAdapter(this,list_cardvist)
        users_list.adapter = adapter

        users_list.setOnItemClickListener { parent, view, position, id ->

        }

        //名刺を交換する処理
        send_card_bt.setOnClickListener({
            var dialog : Dialog = Dialog(this)
            dialog.setTitle("test")
            dialog.setContentView(R.layout.custom_receiver_card)
            var receive_card_img = dialog.findViewById<ImageView>(R.id.receive_card_img)
            receive_card_img.setOnClickListener({
                if( adapter.selectedListCardVisit.size > 0){
                    for(i in 0..adapter.selectedListCardVisit.size - 1){
                        var sendData : JSONObject = JSONObject()
                        var to_user_phone = adapter.selectedListCardVisit[i].phone_number
                        sendData.put("SENDTO_USER" , to_user_phone)
                        socket!!.emit("SEND_CARDVISIT" , sendData)
                    }
                    dialog.dismiss()
                }else{
                    Toast.makeText(this,"送信したいユーザを選択してください!" , Toast.LENGTH_SHORT ).show()
                }

            })
            receive_card_img.setImageBitmap( BitmapFactory.decodeByteArray(front_img_tobyte , 0 , front_img_tobyte.size))
            dialog.window.attributes.windowAnimations = R.style.DialogAnimation_up_bottom;
            dialog.show()
        })

        /// サーバからユーザ一覧の情報のレポンストを受け取る
        socket!!.on("USER_DATA" , {args -> var receiver_data = args[0].toString()
            runOnUiThread {
                var listUser : JSONArray = JSONArray(receiver_data)

                adapter.clear()
                for( i in 0..listUser.length()-1){
                    var jsonObject = listUser.getJSONObject(i)
                    var front_img = jsonObject.getString("front_img")
                    var back_img = jsonObject.getString("back_img")
                    var name = jsonObject.getString("name")
                    var phone_number = jsonObject.getString("phone_number")
//                    var address = jsonObject.getString("address")
                    var adress = ""
                    var email = jsonObject.getString("email")
                    var company_name = jsonObject.getString("company_name")
                    var position = jsonObject.getString("position")
                    var company_url = jsonObject.getString("company_url")
                    var cardvisit : CardVisit = CardVisit(front_img , back_img ,name , phone_number.toInt() , address ,email , company_name , position , company_url)
                    if(jsonObject.getString("phone_number").toInt() != myCardVisit!!.phone_number){
                        adapter.add(cardvisit)
                    }

                }
                adapter.notifyDataSetInvalidated()
            }
        })

        socket!!.on("CARD_DATA" , {args -> var cardVisitJson = args[0] as JSONObject
            runOnUiThread {
                var DIALOG_ANIMATION_STYLE = R.style.DialogAnimation_up_bottom
                var front_img = cardVisitJson.getString("front_img")
                Log.d("card_img " , front_img)
                var dialog : Dialog = Dialog(this)
                dialog.setTitle("test")
                dialog.setContentView(R.layout.custom_receiver_card)
                var receive_card_img = dialog.findViewById<ImageView>(R.id.receive_card_img)
                var delete_bt = dialog.findViewById<ImageView>(R.id.delete_card_visit_bt)
                var save_card_bt = dialog.findViewById<ImageView>(R.id.save_card_visit_bt)
                delete_bt.setOnClickListener {
                    var DIALOG_ANIMATION_STYLE = R.style.DialogAnimation_right_left
                    dialog.dismiss()
                }
                save_card_bt.setOnClickListener {
                    var DIALOG_ANIMATION_STYLE = R.style.DialogAnimation_right_left
                    dialog.dismiss()
                }
                var byte = Base64.decode( front_img.toByteArray() , Base64.DEFAULT)
                receive_card_img.setImageBitmap( BitmapFactory.decodeByteArray(byte , 0 , byte.size))
                dialog.window.attributes.windowAnimations = R.style.DialogAnimation_up_bottom;
                dialog.show()
            }
        })

        users_list.setOnItemClickListener({parent,view ,position,id ->
            selected_user = users[position]
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        socket!!.emit("disconnect" , "")
        socket!!.disconnect()
    }



}

