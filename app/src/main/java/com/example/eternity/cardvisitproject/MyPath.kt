package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path

public class MyPath {
    var s_x: Float = 0f
    var s_y: Float = 0f
    var e_x :Float = 0f
    var e_y : Float = 0f
    var color: Int = Color.BLACK
    var size: Float = 0f

    constructor(s_x: Float, s_y: Float, e_x: Float, e_y: Float, color: Int, size: Float) {
        this.s_x = s_x
        this.s_y = s_y
        this.e_x = e_x
        this.e_y = e_y
        this.color = color
        this.size = size
    }


    fun draw(canvas: Canvas, paint: Paint) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = this.size
        paint.color = this.color
        var path = Path()
        path.moveTo(s_x,s_y)
        path.lineTo(e_x,e_y)
        canvas.drawPath(path, paint)
    }
}