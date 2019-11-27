package com.example.eternity.cardvisitproject

import android.graphics.Color

public class MyTextView{
    var x : Float = 0f
    var y : Float = 0f
    var color : Int = Color.BLACK
    var fontSize : Float = 100f
    var content : String  = "default"

    constructor(x: Float, y: Float, color: Int, content: String) {
        this.x = x
        this.y = y
        this.color = color
        this.content = content
    }
}