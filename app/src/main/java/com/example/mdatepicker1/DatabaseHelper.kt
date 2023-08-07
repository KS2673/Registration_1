package com.example.mdatepicker1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_USERS_TABLE = ("CREATE TABLE " + TABLE_USERS + " ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FIRST_NAME + " TEXT,"
                + KEY_LAST_NAME + " TEXT,"
                + KEY_GENDER + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_Date_Of_Birth + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_SIGN_UP_DATE + " TEXT)")
        db.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        onCreate(db)
    }

    fun addUser(user: User): Boolean {
        val db = this.writableDatabase
        val user_values = ContentValues()
        user_values.put(KEY_FIRST_NAME, user.first_name)
        user_values.put(KEY_LAST_NAME, user.last_name)
        user_values.put(KEY_GENDER, user.gender)
        user_values.put(KEY_EMAIL, user.email)
        user_values.put(KEY_Date_Of_Birth, user.date_of_birth)
        user_values.put(KEY_PASSWORD, user.password)
        user_values.put(KEY_SIGN_UP_DATE, user.signup_date)
        Log.d(DATABASE_NAME, "addUser: Adding " + user + " to " + TABLE_USERS)
        val result = db.insert(TABLE_USERS, null, user_values)
        return if (result == -1L) false else true
    }

    val allUsers: List<Any>
        get() {
            val userList: MutableList<User> = ArrayList<User>()
            val db = this.writableDatabase
            val select_query = "SELECT * FROM " + TABLE_USERS
            val cursor = db.rawQuery(select_query, null)
            if (cursor.moveToFirst()) {
                do {
                    val user = User()
                    user.setid(cursor.getInt(0))
                    user.setFirst_name(cursor.getString(1))
                    user.setLast_name(cursor.getString(2))
                    user.setGender(cursor.getString(3))
                    user.setEmail(cursor.getString(4))
                    user.setDate_of_birth(cursor.getString(5))
                    user.setPassword(cursor.getString(6))
                    user.setSignup_date(cursor.getString(7))
                    userList.add(user)
                } while (cursor.moveToNext())
            }
            return userList
        }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "simple_form_data"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "id"
        private const val KEY_FIRST_NAME = "first_name"
        private const val KEY_LAST_NAME = "last_name"
        private const val KEY_GENDER = "gender"
        private const val KEY_EMAIL = "email"
        private const val KEY_Date_Of_Birth = "date_of_birth"
        private const val KEY_PASSWORD = "password"
        private const val KEY_SIGN_UP_DATE = "signup_date"
    }
}