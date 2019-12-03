package com.example.eternity.cardvisitproject

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

public class DatabaseHelper : SQLiteOpenHelper{

    val CARDVISITTABLENAME : String = "CARDVISIT_TABLE"
    val CARD_TABLE_COL_ID : String = "ID"
    val CARDTABLE_COL_FRONT_IMG : String = "FRONT_IMG"
    val CARDTABLE_COL_BACK_IMG : String = "BACK_IMG"
    val CARDTABLE_COL_USER_ID : String = "USER_ID"


    val USERTABLENAME : String = "USER_TABLE"
    val USERTABLE_COL_ID : String = "ID"
    val USERTABLE_COL_NAME : String ="NAME"
    val USERTABLE_COL_COMPANY_NAME : String = "COMPANY_NAME"
    val USERTABLE_COL_FAX : String ="FAX"
    val USERTABLE_COL_ADRESS : String = "ADRESS"
    val USERTABLE_COL_POSITION : String ="POSITION"
    val USERTABLE_COL_SNS : String = "SNS"
    val USERTABLE_COL_COMPANY_URL : String ="COMPANY_URL"
    val USERTABLE_COL_PHONE : String ="PHONE"
    val USERTABLE_COL_EMAIL : String ="EMAIL"


    constructor(context: Context?, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) : super(
        context,
        name,
        factory,
        version
    )

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE " + USERTABLENAME + " "
                + "( "
                + USERTABLE_COL_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERTABLE_COL_NAME +  " TEXT, "
                + USERTABLE_COL_COMPANY_NAME +  " TEXT, "
                + CARDTABLE_COL_USER_ID +  " TEXT, "
                + USERTABLE_COL_ADRESS +  " TEXT, "
                + USERTABLE_COL_POSITION +  " TEXT, "
                + USERTABLE_COL_SNS +  " TEXT, "
                + USERTABLE_COL_COMPANY_URL +  " TEXT, "
                + USERTABLE_COL_PHONE +  " TEXT, "
                + USERTABLE_COL_EMAIL +  " TEXT "
                + ")" )

        db!!.execSQL("CREATE TABLE " + CARDVISITTABLENAME + " "
                + "( "
                + CARD_TABLE_COL_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CARDTABLE_COL_FRONT_IMG +  " TEXT, "
                + CARDTABLE_COL_BACK_IMG +  " TEXT, "
                + CARDTABLE_COL_USER_ID +  " INTEGER, "
                + "FOREIGN KEY ("+CARDTABLE_COL_USER_ID+") REFERENCES "+USERTABLENAME+" ("+USERTABLE_COL_ID+"));"
                + ")" )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + USERTABLENAME)
        db!!.execSQL("DROP TABLE IF EXISTS " + CARDVISITTABLENAME)
    }
}