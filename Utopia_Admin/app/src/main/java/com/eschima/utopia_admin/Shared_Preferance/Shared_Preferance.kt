package com.eschima.utopia_admin.Shared_Preferance

import android.content.Context

class Shared_Preferance(con: Context) {

    var shared_file = "u_admin_File"
    var shared = con.getSharedPreferences(shared_file, Context.MODE_PRIVATE)

    fun Save_Token(language:String){
        var editor = shared.edit()
        editor.putString("token",language)
        editor.apply()
    }

    fun Return_Token(): String {
        var re:String =  shared.getString("token","0").toString()
        return re
    }


    fun CheckLogin(loginCheck:Boolean){
        var editor = shared.edit()
        editor.putBoolean("check",loginCheck)
        editor.apply()
    }

    fun GetCheckLogin(): Boolean {
        var re:Boolean =  shared.getBoolean("check",false)
        return re
    }

    fun SaveName(name:String){
        var editor = shared.edit()
        editor.putString("name",name)
        editor.apply()
    }

    fun ReturnName(): String {
        var re:String =  shared.getString("name","0").toString()
        return re
    }

    fun SaveImage(image:String){
        var editor = shared.edit()
        editor.putString("image",image)
        editor.apply()
    }

    fun ReturnImage(): String {
        var re:String =  shared.getString("image","0").toString()
        return re
    }

    fun SaveEmail(email:String){
        var editor = shared.edit()
        editor.putString("email",email)
        editor.apply()
    }

    fun ReturnEmail(): String {
        var re:String =  shared.getString("email","0").toString()
        return re
    }

}