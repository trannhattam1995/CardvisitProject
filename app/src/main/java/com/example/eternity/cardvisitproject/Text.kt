package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Paint

public class Text : Shape{
    var content : String = ""

    constructor(x: Float, y: Float, paint: Paint, content: String) : super(x, y, paint) {
        this.content = content
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText(content,x,y,paint)
    }

    override fun isOnTouch(x: Float, y: Float): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun move(new_x: Float, new_y: Float) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}