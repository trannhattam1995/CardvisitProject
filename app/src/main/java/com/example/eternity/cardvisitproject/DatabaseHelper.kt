package com.example.eternity.cardvisitproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.eternity.cardvisitproject.R.id.*

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
        onCreate(db!!)
    }

    //CardVisit情報を追加
    fun SaveCardData(sqLiteDatabase: SQLiteDatabase ,cardVisit: CardVisit){
        var contentValues : ContentValues = ContentValues()
        contentValues.put(CARDTABLE_COL_FRONT_IMG , cardVisit.front_img)
        contentValues.put(CARDTABLE_COL_BACK_IMG , cardVisit.back_img)
        contentValues.put(CARDTABLE_COL_USER_ID , cardVisit.user_id)
        sqLiteDatabase.insert(CARDVISITTABLENAME , null , contentValues)
    }

    //User情報を追加
    fun SavaUser(sqLiteDatabase: SQLiteDatabase , user: User){
        var contentValues : ContentValues = ContentValues()
        contentValues.put(USERTABLE_COL_NAME , user.name)
        contentValues.put(USERTABLE_COL_COMPANY_NAME , user.company_name)
        contentValues.put(USERTABLE_COL_ADRESS , user.address)
        contentValues.put(USERTABLE_COL_POSITION , user.position)
        contentValues.put(USERTABLE_COL_SNS , "test")
        contentValues.put(USERTABLE_COL_COMPANY_URL , user.company_url)
        contentValues.put(USERTABLE_COL_PHONE , user.phone_number)
        contentValues.put(USERTABLE_COL_EMAIL , user.e_mail)

        sqLiteDatabase.insert(USERTABLENAME , null , contentValues)
    }

    fun GetAllCardVisit(sqLiteDatabase: SQLiteDatabase) : ArrayList<CardVisit>{
        var arrayList : ArrayList<CardVisit> = ArrayList()
        var query : String = "SELECT * FROM " + CARDVISITTABLENAME + " ;"
        var cursor : Cursor = sqLiteDatabase.rawQuery(query , null)
        while (cursor.moveToNext()){
            var front_img : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_FRONT_IMG))
            var back_img : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_BACK_IMG))
            var user_id : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_ID))
            var cardVisit : CardVisit = CardVisit(front_img , back_img , user_id.toInt())
            arrayList.add(cardVisit)
        }
        cursor.close()

        return arrayList
    }

    fun GetAllUser(sqLiteDatabase: SQLiteDatabase) : ArrayList<User>{
        var arrayList : ArrayList<User> = ArrayList()
        var query : String = "SELECT * FROM " + USERTABLENAME + " ;"
        var cursor : Cursor = sqLiteDatabase.rawQuery(query , null)
        while (cursor.moveToNext()){
            var id : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_ID))
            var name : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_NAME))
            var phone : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_PHONE))
            var adress : String = cursor.getString(cursor.getColumnIndex(CARDTABLE_COL_USER_ID))
            var email : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_EMAIL))
            var company_name : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_COMPANY_NAME))
            var position : String = cursor.getString(cursor.getColumnIndex(USERTABLE_COL_POSITION))
            var company_url : String = cursor.getString((cursor.getColumnIndex(USERTABLE_COL_COMPANY_URL)))
            var user : User = User(id.toInt() , name , phone.toInt() ,adress ,email ,company_name ,position  , company_url)
            arrayList.add(user)
        }
        cursor.close()

        return arrayList
    }
}