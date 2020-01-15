package com.example.eternity.cardvisitproject

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.objects.FirebaseVisionObjectDetectorOptions
import kotlinx.android.synthetic.main.activity_register_new_card_visit.*

class RegisterNewCardVisitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_card_visit)

        var picture_array = intent.getByteArrayExtra("picture")
        var picture_bitmap : Bitmap = BitmapFactory.decodeByteArray(picture_array , 0 , picture_array.size)
        picture_view.setImageBitmap(picture_bitmap)

        val image = FirebaseVisionImage.fromBitmap(picture_bitmap)

//        val detector = FirebaseVision.getInstance()
//            .onDeviceTextRecognizer
//
//        val result = detector.processImage(image)
//            .addOnSuccessListener { firebaseVisionText ->
//               //result_text.setText(firebaseVisionText.text)
//            }
//            .addOnFailureListener { e ->
//                // Task failed with an exception
//                // ...
//            }


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
                if( detectedObjects.size > 0){
                    var obj = detectedObjects[0]
                    var bounds = obj.boundingBox
                    var new_bitmap = Bitmap.createBitmap(picture_bitmap , bounds.centerX() - bounds.width() / 2  ,bounds.centerY() - bounds.height()/2 , bounds.width() , bounds.height() )
                    picture_view.setImageBitmap(new_bitmap)
                }
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }
    }
}
