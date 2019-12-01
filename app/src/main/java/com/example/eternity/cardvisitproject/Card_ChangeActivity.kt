package com.example.eternity.cardvisitproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import io.socket.client.IO
import kotlinx.android.synthetic.main.activity_card__change.*
import org.json.JSONArray
import org.json.JSONObject


class Card_ChangeActivity : AppCompatActivity() {

    var socket : io.socket.client.Socket? = null
    var URL = "http://192.168.0.13:3000"

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



        // 名刺を交換する処理
        var users : ArrayList<String> = ArrayList()

        //サーバーにアクセス
        socket = IO.socket(URL)
        socket!!.connect()

        var data : JSONObject

        find_user_bt.setOnClickListener({
            socket!!.emit("resquest" , "test")
        })

        var adapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1, users)
        users_list.adapter = adapter

        socket!!.on("user-data" , {args -> data = args[0] as JSONObject
            runOnUiThread {

                var listUser : JSONArray = data.getJSONArray("data")
                for( i in 0..listUser.length()){
                    adapter.add(listUser.optString(i))
                }
                adapter.notifyDataSetInvalidated()
            }
        })



    }


}

