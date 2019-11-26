package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Paint

public class Triangle : Shape{
    var e_x : Float = 0f
    var e_y : Float = 0f

    constructor(x: Float, y: Float, paint: Paint, e_x: Float, e_y: Float) : super(x, y, paint) {
        this.e_x = e_x
        this.e_y = e_y
    }

    constructor(x: Float, y: Float, paint: Paint) : super(x, y, paint)




    override fun draw(canvas: Canvas) {
        canvas.drawLine(x,y , x,e_y , paint)
        canvas.drawLine(x,y , e_x,y , paint)
        canvas.drawLine(e_x,y , e_x,e_y , paint)
        canvas.drawLine(x,e_y , e_x,e_y , paint)
    }

    override fun isOnTouch(x: Float, y: Float): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun move(new_x: Float, new_y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}