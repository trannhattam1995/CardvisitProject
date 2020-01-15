package com.example.eternity.cardvisitproject

///import android.support.v4.app.ActivityCompat
//import android.support.v4.content.ContextCompat
//import android.support.v7.app.AppCompatActivity
import android.Manifest
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions

class CameraActivity : AppCompatActivity() {

    private var mCamera: Camera? = null
    private var mPreview: CameraPreview? = null

    /// カメラ権限のリクエストを受け取り番号
    private val MY_PERMISSIONS_REQUEST_CAMERA = 111


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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

        try{
            mCamera!!.startPreview()

            var bitmap : Bitmap = BitmapFactory.decodeByteArray(data,0,data.size)
            var detectedBitmap : Bitmap? = null
            val image = FirebaseVisionImage.fromBitmap(bitmap)
            // Multiple object detection in static images
            val options = FirebaseVisionObjectDetectorOptions.Builder()
                .setDetectorMode(FirebaseVisionObjectDetectorOptions.SINGLE_IMAGE_MODE)
                .enableMultipleObjects()
                .enableClassification()  // Optional
                .build()

            // Or, to change the default settings:
            val objectDetector = FirebaseVision.getInstance().getOnDeviceObjectDetector(options)

            objectDetector.processImage(image)
                .addOnSuccessListener { detectedObjects ->
                    Log.d( "tracking object", "" + detectedObjects.size)
                    // The list of detected objects contains one item if multiple object detection wasn't enabled.
                    var object_text : String = ""
                    for (obj in detectedObjects) {
                        val id = obj.trackingId       // A number that identifies the object across images
                        val bounds = obj.boundingBox  // The object's position in the image

                        // If classification was enabled:
                        val category = obj.classificationCategory
                        val confidence = obj.classificationConfidence
                        object_text += "" + id + " " + bounds + " " + category + " \n"

                    }
                    Log.d("tracking " , object_text)
                    if( detectedObjects.size > 0){
                        Log.d("track" , "in here")
                        var obj = detectedObjects[0]
                        var bounds = obj.boundingBox
                        detectedBitmap = Bitmap.createBitmap(bitmap , bounds.centerX() - bounds.width() / 2  ,bounds.centerY() - bounds.height()/2 , bounds.width() , bounds.height() )
                    }
                }
                .addOnFailureListener { e ->
                    // Task failed with an exception
                    // ...
                }

            var dialog : Dialog = Dialog(this)
            dialog.setTitle("test")
            dialog.setContentView(R.layout.custom_receiver_card)
            var receive_card_img = dialog.findViewById<ImageView>(R.id.receive_card_img)
            receive_card_img.setOnClickListener({
                dialog.dismiss()
            })
            receive_card_img.setImageBitmap(detectedBitmap)
            dialog.window.attributes.windowAnimations = R.style.DialogAnimation_up_bottom;
            dialog.show()
//            var intent : Intent = Intent(this , RegisterNewCardVisitActivity::class.java)
//            intent.putExtra("picture" , data)
//            startActivity(intent)
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
