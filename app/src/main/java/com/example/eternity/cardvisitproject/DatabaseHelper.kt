package com.example.eternity.cardvisitproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DatabaseHelper : SQLiteOpenHelper{

    val CARDVISITTABLENAME : String = "CARDVISIT_TABLE"
    val CARD_TABLE_COL_ID : String = "ID"
    val CARDTABLE_COL_FRONT_IMG : String = "FRONT_IMG"
    val CARDTABLE_COL_BACK_IMG : String = "BACK_IMG"
    val CARDTABLE_COL_USER_NAME : String = "USER_NAME"
    val CARDTABLE_COL_USER_PHONE_NUMBER : String = "PHONE_NUMBER"
    val CARDTABLE_COL_USER_ADDRESS : String = "USER_ADDRESS"
    val CARDTABLE_COL_USER_EMAIL : String = "EMAIL"
    val CARDTABLE_COL_USER_SNS : String = "SNS"
    val CARDTABLE_COL_USER_COMPANY_NAME : String = "COMPANY_NAME"
    val CARDTABLE_COL_USER_POSITION : String = "POSITION"
    val CARDTABLE_COL_USER_COMPANY_URL : String = "USER_COMPANY_URL"


    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(
        context,
        name,
        factory,
        version
    )

    override fun onCreate(db: SQLiteDatabase?) {

        db!!.execSQL("CREATE TABLE " + CARDVISITTABLENAME + " "
                + "( "
                + CARD_TABLE_COL_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CARDTABLE_COL_FRONT_IMG +  " TEXT, "
                + CARDTABLE_COL_BACK_IMG +  " TEXT, "
                + CARDTABLE_COL_USER_NAME +  " TEXT, "
                + CARDTABLE_COL_USER_PHONE_NUMBER +  " TEXT, "
                + CARDTABLE_COL_USER_ADDRESS +  " TEXT, "
                + CARDTABLE_COL_USER_EMAIL +  " TEXT, "
                + CARDTABLE_COL_USER_SNS +  " TEXT, "
                + CARDTABLE_COL_USER_COMPANY_NAME +  " TEXT, "
                + CARDTABLE_COL_USER_POSITION +  " TEXT, "
                + CARDTABLE_COL_USER_COMPANY_URL +  " TEXT "
                + ")" )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + CARDVISITTABLENAME)
        onCreate(db!!)
    }

    fun addCardVisit(sqLiteDatabase: SQLiteDatabase , cardVisit: CardVisit){
        var content : ContentValues = ContentValues()
        content.put(CARDTABLE_COL_FRONT_IMG , cardVisit.front_img)
        content.put(CARDTABLE_COL_BACK_IMG , cardVisit.back_img)
        content.put(CARDTABLE_COL_USER_NAME , cardVisit.name)
        content.put(CARDTABLE_COL_USER_PHONE_NUMBER , cardVisit.phone_number)
        content.put(CARDTABLE_COL_USER_ADDRESS , cardVisit.address)
        content.put(CARDTABLE_COL_USER_EMAIL , cardVisit.email)
        content.put(CARDTABLE_COL_USER_SNS , cardVisit.sns)
        content.put(CARDTABLE_COL_USER_COMPANY_NAME , cardVisit.company_name)
        content.put(CARDTABLE_COL_USER_POSITION , cardVisit.position)
        content.put(CARDTABLE_COL_USER_COMPANY_URL , cardVisit.company_url)

        sqLiteDatabase.insertOrThrow(CARDVISITTABLENAME, null, content)
    }

    fun GetAllUser(sqLiteDatabase: SQLiteDatabase) : ArrayList<CardVisit>{
        var arrayList : ArrayList<CardVisit> = ArrayList()
        var query : String = "SELECT * FROM " + CARDVISITTABLENAME + " ;"
        var cursor : Cursor = sqLiteDatabase.rawQuery(query , null)
        while (cursor.moveToNext()){
            var id : String = cursor.getString(cursor.getColumnIndex(CARD_TABLE_COL_ID))
            var front_img : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_FRONT_IMG))
            var back_img : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_BACK_IMG))
            var name : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_NAME))
            var phone : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_PHONE_NUMBER))
            var adress : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_ADDRESS))
            var email : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_EMAIL))
            var sns : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_SNS))
            var company_name : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_COMPANY_NAME))
            var position : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_POSITION))
            var company_url : String = cursor.getString((cursor.getColumnIndex(CARDTABLE_COL_USER_COMPANY_URL)))
            var cardVisit : CardVisit = CardVisit(front_img , back_img ,  name , phone.toInt() ,adress ,email ,company_name ,position  , company_url)
            arrayList.add(cardVisit)
        }
        cursor.close()

        return arrayList
    }
}