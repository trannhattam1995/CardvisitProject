package com.example.eternity.cardvisitproject

import android.app.Dialog
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import io.socket.client.IO
import kotlinx.android.synthetic.main.activity_card__change.*
import org.json.JSONArray
import org.json.JSONObject


class Card_ChangeActivity : AppCompatActivity() {

    var socket : io.socket.client.Socket? = null
    //var URL = "http://192.168.0.13:3000"
    var URL = "http://172.30.27.194:3000"
    var SEVER = "RITSUKO-PC"

    var user : User? = null
    var selected_user : String? = null

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
        var preference  = getSharedPreferences("cardImage" , Context.MODE_PRIVATE)
        var img_decode : String = preference.getString("image" , "")
        var byte = Base64.decode( img_decode.toByteArray() , Base64.DEFAULT)
        var id : String = preference.getString("id","1")
        var name : String = preference.getString("name" , "test2")
        var phone : String = preference.getString("phone" , "0")
        var address : String = preference.getString("address" , "")
        var email : String = preference.getString("email" , "")
        var sns : String = preference.getString("sns" , "")
        var company_name : String = preference.getString("company_name" , "")
        var position : String = preference.getString("sns" , "")
        var company_url : String = preference.getString("company_name" , "")

        user = User(id.toInt() ,name ,phone.toInt() ,address , email , company_name ,position , company_url)
        // 名刺を交換する処理
        var users : ArrayList<String> = ArrayList()

        //サーバーにアクセス
        socket = IO.socket(URL)
        socket!!.connect()

        var data : JSONObject = JSONObject()
        data.put("phone_number", user!!.phone_number.toString())
        data.put("name", user!!.name)
        socket!!.emit("find_user" , data)

        var adapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1, users)
        users_list.adapter = adapter

        send_card_bt.setOnClickListener({
            var dialog : Dialog = Dialog(this)
            dialog.setTitle("test")
            dialog.setContentView(R.layout.custom_receiver_card)
            var receive_card_img = dialog.findViewById<ImageView>(R.id.receive_card_img)
            receive_card_img.setOnClickListener({
                var sendData : JSONObject = JSONObject()
                sendData.put("sendto" , selected_user)
                sendData.put("mycard" ,img_decode )

                socket!!.emit("send_cardvisit" , sendData)
                dialog.dismiss()
            })
            var byte = Base64.decode( img_decode.toByteArray() , Base64.DEFAULT)
            receive_card_img.setImageBitmap( BitmapFactory.decodeByteArray(byte , 0 , byte.size))
            dialog.window.attributes.windowAnimations = R.style.DialogAnimation_up_bottom;
            dialog.show()
        })

        socket!!.on("user-data" , {args -> var receiver_data = args[0].toString()
            runOnUiThread {
                var listUser : JSONArray = JSONArray(receiver_data)
                adapter.clear()
                for( i in 0..listUser.length()-1){
                    var jsonObject = listUser.getJSONObject(i)
                    if(jsonObject.getString("phone_number").toInt() != user!!.phone_number){
                        adapter.add(jsonObject.getString("phone_number"))
                    }

                }
                adapter.notifyDataSetInvalidated()
            }
        })

        socket!!.on("card-data" , {args -> var receiver_data = args[0] as JSONObject
            runOnUiThread {

                var card_img : String = receiver_data.getString("data")
                var dialog : Dialog = Dialog(this)
                dialog.setTitle("test")
                dialog.setContentView(R.layout.custom_receiver_card)
                var receive_card_img = dialog.findViewById<ImageView>(R.id.receive_card_img)
                var byte = Base64.decode( card_img.toByteArray() , Base64.DEFAULT)
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
        var data : JSONObject = JSONObject()
        data.put("phone_number", user!!.phone_number.toString())
        Toast.makeText(this,user!!.phone_number.toString(),Toast.LENGTH_SHORT).show()
        socket!!.emit("disconnect" , data)
        socket!!.disconnect()
    }



}

