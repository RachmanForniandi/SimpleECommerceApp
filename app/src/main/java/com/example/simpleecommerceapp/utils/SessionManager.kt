package com.example.simpleecommerceapp.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (val context:Context){
    var pref: SharedPreferences
    var editor:SharedPreferences.Editor

    var PRIVATE_MODE =0
    var PREF_NAME = "OneDayLogin"

    init {
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {
        private val KEY_TOKEN = "tokenLogin"
        private val KEY_LOGIN = "isLogin"
    }

    //membuat session login
    fun createLoginSession(token:String){
        editor.putString(KEY_TOKEN,token)
        editor.putBoolean(KEY_LOGIN,true)
        editor.commit()
        //commit berguna untuk menyimpan perubahan
    }

    //logout user
    fun logout(){
        editor.clear()
        editor.commit()
    }

    //cek login
    val isLogin: Boolean get() = pref.getBoolean(KEY_LOGIN,false)

    var idUser: String? get()= pref.getString("iduser","")
    set(value) {
        editor.putString("iduser",value)
        editor.commit()
    }
}