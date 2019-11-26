package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Paint

abstract class Shape{
    var x : Float = 0f
    var y : Float = 0f
    var paint : Paint = Paint()

    constructor(x: Float, y: Float, paint: Paint) {
        this.x = x
        this.y = y
        this.paint = paint
    }

    abstract fun draw(canvas: Canvas)
    abstract fun isOnTouch(point_x:Float ,point_y : Float) : Boolean
    abstract fun move(new_x:Float , new_y:Float)

}