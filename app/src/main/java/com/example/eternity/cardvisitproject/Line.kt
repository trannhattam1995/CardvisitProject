package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Paint

public class Line : Shape{
    var e_x : Float = 0f
    var e_y : Float = 0f

    constructor(x: Float, y: Float, e_x: Float, e_y: Float, paint: Paint) : super(x, y, paint) {
        this.e_x = e_x
        this.e_y = e_y
    }

    override fun draw(canvas: Canvas) {
        canvas.drawLine(x,y,e_x,e_y,paint)
    }

    override fun isOnTouch(point_x: Float, point_y: Float): Boolean {
        if( x <= point_x && point_x <= e_x && y < point_y && point_y <= e_y) return true
        return false
    }

    override fun move(new_x: Float, new_y: Float) {
        e_x = new_x + e_x - x
        e_y = new_y + e_y - y
        x = new_x
        y = new_y
    }

    fun update(x: Float, y: Float, e_x: Float, e_y: Float, paint: Paint) {
        super.x = x
        super.y = y
        super.paint = paint
        this.e_x = e_x
        this.e_y = e_y
    }
}