package com.example.baitaplon.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.baitaplon.model.Expense


class ExpenseDatabase(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        private val DB_NAME = "DBexpenses" +
                ""
        private val DB_VERSION = 1;
        private val TABLE_NAME = "expense"
        private val ID = "ExpenseID"
        private val EXPENSE_NAME_ITEM = "ExpenseNameItem"
        private val EXPENSE_AMOUNT = "ExpenseAmount"
        private val EXPENSE_NOTE = "ExpenseNote"
        private val EXPENSE_DATE = "ExpenseDate"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val create_table = ("CREATE TABLE " + TABLE_NAME + "("
                + ID + " Integer primary key , "
                + EXPENSE_NAME_ITEM + " TEXT, "
                + EXPENSE_AMOUNT + " TEXT, "
                + EXPENSE_NOTE + " TEXT, "
                + EXPENSE_DATE + " TEXT" + ")")
        if (db != null) {
            db.execSQL(create_table)
        }
        // Cau lenh tao bang

    }

    fun insertExpense(exp: Expense): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(EXPENSE_NAME_ITEM, exp.ExpenseItem)
        values.put(EXPENSE_AMOUNT, exp.ExpenseAmount)
        values.put(EXPENSE_NOTE, exp.ExpenseNote)
        values.put(EXPENSE_DATE, exp.ExpenseDate)
        val _success = db.insert(TABLE_NAME,null, values)
        db.close()
        return _success
    }
    fun getAllData():ArrayList<Expense>{
        val listExp:ArrayList<Expense> = ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME"
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectALLQuery, null)
        }catch(e: java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectALLQuery)
            return ArrayList()
        }
        var idExp: Int
        var nameitemExp: String
        var amountExp:String
        var noteExp:String
        var dateExp :String
        if(cursor.moveToFirst()){
            do{
                idExp = cursor.getInt(cursor.getColumnIndex(ID))
                nameitemExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NAME_ITEM))
                amountExp = cursor.getString(cursor.getColumnIndex(EXPENSE_AMOUNT))
                noteExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NOTE))
                dateExp = cursor.getString(cursor.getColumnIndex(EXPENSE_DATE))
                val expNew = Expense(idExp.toString(), nameitemExp, amountExp, noteExp, dateExp)
                listExp.add(expNew)

            }while (cursor.moveToNext())

        }
        return  listExp
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
    fun fillterMonth(s: String) : ArrayList<Expense>{
        val listExp:ArrayList<Expense> = ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME WHERE $EXPENSE_DATE LIKE '%$s%' "
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectALLQuery, null)
        }catch(e: java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectALLQuery)
            return ArrayList()
        }
        var idExp: Int
        var nameitemExp: String
        var amountExp:String
        var noteExp:String
        var dateExp :String
        if(cursor.moveToFirst()){
            do{
                idExp = cursor.getInt(cursor.getColumnIndex(ID))
                nameitemExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NAME_ITEM))
                amountExp = cursor.getString(cursor.getColumnIndex(EXPENSE_AMOUNT))
                noteExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NOTE))
                dateExp = cursor.getString(cursor.getColumnIndex(EXPENSE_DATE))
                val expNew = Expense(idExp.toString(), nameitemExp, amountExp, noteExp, dateExp)
                listExp.add(expNew)

            }while (cursor.moveToNext())

        }
        return  listExp
    }
    fun fillterItem(nameMonth: String, nameItem:String):ArrayList<Expense>{
        val listExp:ArrayList<Expense> = ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME WHERE $EXPENSE_NAME_ITEM LIKE '%$nameItem%' AND $EXPENSE_DATE LIKE '$nameMonth'  "
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectALLQuery, null)
        }catch(e: java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectALLQuery)
            return ArrayList()
        }
        var idExp: Int
        var nameitemExp: String
        var amountExp:String
        var noteExp:String
        var dateExp :String
        if(cursor.moveToFirst()){
            do{
                idExp = cursor.getInt(cursor.getColumnIndex(ID))
                nameitemExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NAME_ITEM))
                amountExp = cursor.getString(cursor.getColumnIndex(EXPENSE_AMOUNT))
                noteExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NOTE))
                dateExp = cursor.getString(cursor.getColumnIndex(EXPENSE_DATE))
                val expNew = Expense(idExp.toString(), nameitemExp, amountExp, noteExp, dateExp)
                listExp.add(expNew)

            }while (cursor.moveToNext())

        }
        return  listExp
    }

    fun deleteExpense(_id: String): Boolean {
        val db = this.writableDatabase
        val _success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id)).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }
    fun fillterItem(nameItem:String):ArrayList<Expense>{
        val listExp:ArrayList<Expense> = ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME WHERE $EXPENSE_NAME_ITEM LIKE '%$nameItem%'  "
        val cursor: Cursor?
        try{
            cursor = db.rawQuery(selectALLQuery, null)
        }catch(e: java.lang.Exception){
            e.printStackTrace()
            db.execSQL(selectALLQuery)
            return ArrayList()
        }
        var idExp: Int
        var nameitemExp: String
        var amountExp:String
        var noteExp:String
        var dateExp :String
        if(cursor.moveToFirst()){
            do{
                idExp = cursor.getInt(cursor.getColumnIndex(ID))
                nameitemExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NAME_ITEM))
                amountExp = cursor.getString(cursor.getColumnIndex(EXPENSE_AMOUNT))
                noteExp = cursor.getString(cursor.getColumnIndex(EXPENSE_NOTE))
                dateExp = cursor.getString(cursor.getColumnIndex(EXPENSE_DATE))
                val expNew = Expense(idExp.toString(), nameitemExp, amountExp, noteExp, dateExp)
                listExp.add(expNew)

            }while (cursor.moveToNext())

        }
        return  listExp
    }
}