package com.eschima.utopia_admin.Pages.Splash_Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eschima.utopia_admin.MainActivity
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance

class Splash_Screen : AppCompatActivity() {

    lateinit var Shared : Shared_Preferance
    var check :Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash__screen)

        Shared  = Shared_Preferance(this)
        check = Shared.GetCheckLogin()
        waiting()

    }

    private fun waiting() {

        val mythread = object : Thread() {
            override fun run() {

                try {

                    sleep(3000)

                    if(check){
                        // the user is loging
                        var intent = Intent(applicationContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        // the user is not login
                        var intent = Intent(applicationContext, Login::class.java)
                        startActivity(intent)
                        finish()
                    }


                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

        }
        mythread.start()
    }
}
