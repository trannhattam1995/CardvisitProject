package com.example.eternity.cardvisitproject

public class CardVisit{
    var id : Int? = null
    var front_img : String? = null
    var back_img : String? = null
    var name: String? = null
    var phone_number: Int = 0
    var address: String? = null
    var e_mail: String? = null
    val sns: String? = null
    var company_name: String? = null
    var position: String? = null
    var company_url: String? = null

    constructor(
        front_img: String?,
        back_img: String?,
        name: String?,
        phone_number: Int,
        address: String?,
        e_mail: String?,
        company_name: String?,
        position: String?,
        company_url: String?
    ) {
        this.front_img = front_img
        this.back_img = back_img
        this.name = name
        this.phone_number = phone_number
        this.address = address
        this.e_mail = e_mail
        this.company_name = company_name
        this.position = position
        this.company_url = company_url
    }
}