package com.eschima.utopia_admin.Pages.Splash_Login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.eschima.utopia_admin.MainActivity
import com.eschima.utopia_admin.Model.Login.login
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import com.google.android.material.snackbar.Snackbar
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Login : AppCompatActivity() {

    lateinit var dialog: SpotsDialog
    lateinit var Shared : Shared_Preferance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        Shared = Shared_Preferance(this)

        login2_btn.setOnClickListener {

            var username: String = username_text.text.toString()
            var password : String = password_text.text.toString()

            if(InternerConnection()){
                login_auth(username,password)
            }
        }
    }


    fun InternerConnection(): Boolean {
        val conn = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = conn.activeNetworkInfo
        if(network != null && network.isConnected){
            return true
        }else{

            Snackbar.make(login_avtivity_id, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.BLUE).show()
            return false
        }
    }

    private fun login_auth(email :String , password :String){

        dialog.show()
        try {

            var client = ServiceGenerator.createService(APIClient::class.java)

            val call: Call<login> = client.log_in(email,password)
            call.enqueue(object : Callback, retrofit2.Callback<login> {
                override fun onResponse(
                    call: Call<login>,
                    response: Response<login>
                ) {
                    if(response.isSuccessful){
                        dialog.dismiss()

                        if(response.body()!!.data.accountType.equals("Teacher")){
                            Shared.Save_Token(response.body()!!.data.token.toString())
                            Shared.SaveEmail(response.body()!!.data.email.toString())
                            Shared.SaveImage("https://Utopials.com"+response.body()!!.data.imagePath.toString())
                            //Shared.SaveName(response.body()!!.data.token.toString())

                            // login check
                            Shared.CheckLogin(true)
                            var intent = Intent(applicationContext, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(applicationContext, "This account is not for a Teacher", Toast.LENGTH_LONG).show()

                        }



                    }else{
                        dialog.dismiss()
                        Toast.makeText(applicationContext, "Username Or Password Incorrect", Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<login>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(applicationContext, "check Internet Connection", Toast.LENGTH_LONG).show()
                }

            })
        } catch (e: Exception) {

            Log.e("error",e.toString())
        }
    }

}
