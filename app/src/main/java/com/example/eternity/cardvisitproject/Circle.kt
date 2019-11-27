package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Circle : Shape{
    var r : Float = 0f

    constructor(x: Float, y: Float, r: Float, paint: Paint) : super(x, y, paint) {
        this.r = r
    }


    override fun draw(canvas: Canvas){
        canvas.drawCircle(this.x,this.y,this.r , this.paint)
    }

    override fun isOnTouch(point_x: Float, point_y: Float): Boolean {
        var distan : Float = Math.abs( point_x - x) * Math.abs( point_x - x) + Math.abs( point_y - y) * Math.abs( point_y - y)
        if( distan < r * r ){
            return true
        }
        return false
    }

    override fun move(new_x: Float, new_y: Float) {
        super.x = new_x
        super.y = new_y
    }

    fun update(x: Float, y: Float, r : Float, paint: Paint) {
        super.x = x
        super.y = y
        super.paint.color = paint.color
        this.r = r
    }
}
