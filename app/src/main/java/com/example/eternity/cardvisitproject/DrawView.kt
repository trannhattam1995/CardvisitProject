package com.example.eternity.cardvisitproject
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawView : View {
    var paint : Paint = android.graphics.Paint()
    var path : Path = Path()



    constructor(context: Context) : super(context) {
        path = Path()

        paint = Paint()
        paint.color = -0xff7800
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.setStrokeWidth(10f)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        path = Path()

        paint = Paint()
        paint.color = -0xff7800
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.setStrokeWidth(10f)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPath(path, paint)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.getX()
        val y = event.getY()

        when (event.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
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
