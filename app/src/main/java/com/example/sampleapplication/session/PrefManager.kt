package com.example.sampleapplication.session

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.installations.Utils

class PrefManager(var context: Context) {
    val pref: SharedPreferences? = context.getSharedPreferences("mySharedPreference", Context.MODE_PRIVATE)
    val edit: SharedPreferences.Editor? = pref?.edit()

    fun saveUser(email: String?){
        edit?.putString("email", email)
        edit?.commit()
    }

    fun getUser(): String?{
        return pref?.getString("email","")
    }

    fun setLang(lang: String?){
        edit?.putString("lang", lang)
        edit?.commit()
    }

    fun getLang():String?{
        return pref?.getString("lang","")
    }

    fun clear(){
        edit?.clear()
        edit?.commit()
        edit?.apply()
    }
}