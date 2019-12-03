package com.example.eternity.cardvisitproject

import android.media.Image

public class CardVisit{
    var front_img : String? = null
    var back_img : String? = null
    var user_id : Int? = null

    constructor(front_img: String?, back_img: String?, user_id: Int) {
        this.front_img = front_img
        this.back_img = back_img
        this.user_id = user_id
    }
}