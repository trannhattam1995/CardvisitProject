package com.example.eternity.cardvisitproject

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import android.support.annotation.NonNull
import android.view.View


class Card_ChangeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card__change)
        Log.d("GPS", "change display")
        // 位置情報使用時に準備
        if ((PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION  )) and
            (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)))
        {
            // 位置情報を管理している LocationManager のインスタンスを生成
            var locationManager: LocationManager? = getSystemService(LOCATION_SERVICE) as LocationManager
            var locationProvider : String = ""

            if (null !== locationManager) {
                // GPSが利用可能になっているかどうかをチェック
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationProvider = LocationManager.GPS_PROVIDER
                } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationProvider = LocationManager.NETWORK_PROVIDER
                } else {
                    // いずれも利用可能でない場合は、GPSを設定する画面に
                    val settingsIntent = Intent(ACTION_LOCATION_SOURCE_SETTINGS)
                    startActivity(settingsIntent)
                    return
                }

                // 変化検出時に呼ばれる
                locationManager.requestLocationUpdates(
                    locationProvider,
                    500, // 通知のための最小時間間隔（ミリ秒）
                    1.0F, // 通知のための最小距離間隔（メートル）
                    object : LocationListener {
                        override fun onLocationChanged(location: Location) {
                            // ここで座標をもとめている
                            val lat = location.latitude
                            val lng = location.longitude
                            Log.d("GPS", lat.toString() + " " + lng.toString())


                        }
                        override fun onProviderDisabled(provider: String) {}
                        override fun onProviderEnabled(provider: String) {}
                        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
                    }
                )
            }
        }
    }
}

