package com.example.eternity.cardvisitproject
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView : View {
    var paint : Paint = android.graphics.Paint()
    var path : Path = Path()

    var myTextViews  : ArrayList<MyTextView> = ArrayList()

    constructor(context: Context) : super(context) {
        path = Path()

        paint = Paint()
        paint.color = -0xff7800

        paint.setStrokeWidth(10f)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        path = Path()

        paint = Paint()
        paint.color = -0xff7800

        paint.setStrokeWidth(30f)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var bitmap : Bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources , R.drawable.temple1) , this.width,this.height, false )
        canvas.drawBitmap(bitmap , 0f , 0f , paint)

        myTextViews.add(MyTextView(50f,50f,Color.RED , "test"))
        for(textview in myTextViews){
            paint.color = textview.color
            paint.textSize = textview.fontSize
            canvas.drawText( textview.content , textview.x , textview.y ,paint)
        }

        ///canvas.dra

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.getX()
        val y = event.getY()

        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> {

                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(x, y)

                invalidate()
            }
        }
        return true
    }
}
