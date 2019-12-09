package com.example.eternity.cardvisitproject

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.hardware.Camera
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.ImageButton

class CameraActivity : AppCompatActivity() {

    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null

    /// カメラ権限のリクエストを受け取り番号
    private val MY_PERMISSIONS_REQUEST_CAMERA = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    MY_PERMISSIONS_REQUEST_CAMERA)

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{
            // Create an instance of Camera
            mCamera = getCameraInstance()

            mPreview = mCamera?.let {
                // Create our Preview view
                CameraPreview(this, it)
            }

            // Set the Preview view as the content of our activity.
            mPreview?.also {
                val preview: FrameLayout = findViewById(R.id.camera_preview)
                preview.addView(it)
            }

            val captureButton: ImageButton = findViewById(R.id.button_capture)
            captureButton.setOnClickListener {
                // get an image from the camera
                mCamera?.takePicture(null, null, mPicture)
            }
        }
    }

    private val mPicture = Camera.PictureCallback { data, _ ->

        var bitmap : Bitmap
        try{
            var intent : Intent = Intent(this , RegisterNewCardVisitActivity::class.java)
            intent.putExtra("picture" , data)
            startActivity(intent)
        }catch (ex: Exception){

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMISSIONS_REQUEST_CAMERA->{
                // Create an instance of Camera
                mCamera = getCameraInstance()

                mPreview = mCamera?.let {
                    // Create our Preview view
                    CameraPreview(this, it)
                }

                // Set the Preview view as the content of our activity.
                mPreview?.also {
                    val preview: FrameLayout = findViewById(R.id.camera_preview)
                    preview.addView(it)
                }
            }
        }
    }

    /** A safe way to get an instance of the Camera object. */
    fun getCameraInstance(): Camera? {
        return try {
            Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            null // returns null if camera is unavailable
        }
    }
}
