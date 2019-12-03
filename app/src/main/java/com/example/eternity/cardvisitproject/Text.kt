package com.example.eternity.cardvisitproject

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect

public class Text : Shape{
    var content : String = ""

    constructor(x: Float, y: Float, paint: Paint, content: String) : super(x, y, paint) {
        this.content = content
        super.paint.textSize = paint.textSize
        super.paint.color = paint.color
    }

    override fun draw(canvas: Canvas) {
        canvas.drawText(content,x,y,paint)
    }

    override fun isOnTouch(point_x: Float, point_y: Float): Boolean {
        var rect : Rect = Rect()
        paint.getTextBounds(content , x.toInt(), y.toInt() ,rect)
        if( x <= point_x && point_x <= x + rect.width() && y < point_y && point_y <= y + rect.height()) return true
        return false
    }

    override fun move(new_x: Float, new_y: Float) {
        x = new_x
        y = new_y
    }

    fun update(x: Float, y: Float, e_x: Float, e_y: Float, paint: Paint) {
        super.x = x
        super.y = y
        super.paint.color = paint.color
        super.paint.textSize = paint.textSize
    }
}