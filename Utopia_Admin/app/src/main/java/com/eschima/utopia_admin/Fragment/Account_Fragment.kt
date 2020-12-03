package com.eschima.utopia_admin.Fragment


import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import com.bumptech.glide.Glide
import com.eschima.utopia_admin.Model.Login.current_user
import com.eschima.utopia_admin.Pages.Splash_Login.Login

import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.fragment_account_.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class Account_Fragment : Fragment() {

    lateinit var Shared : Shared_Preferance
    lateinit var con : Context
    lateinit var gialog : KProgressHUD

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        con = context as Activity
        return inflater.inflate(R.layout.fragment_account_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Shared =  Shared_Preferance(con)
        scale()
        getLocalData()

        if(InternerConnection()){
            gialog=   KProgressHUD.create(con)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            gialog.setBackgroundColor(Color.parseColor("#522158"))
            gialog.show()

            if(Shared.Return_Token()!= "0"){
                get_info_data( Shared.Return_Token())
            }
        }


        logout_btn.setOnClickListener {

            Shared.CheckLogin(false)
            var intent = Intent(con, Login::class.java)
            startActivity(intent)
            getActivity()!!.onBackPressed();

        }
    }


    fun get_info_data( token:String){

        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.get_current_user_info(token)
        call.enqueue(object : Callback, retrofit2.Callback<current_user> {
            override fun onFailure(call: Call<current_user>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<current_user>, response: Response<current_user>) {

                if (response.isSuccessful){
                    gialog.dismiss()

                    val email = response.body()!!.data.user.email

                    email_info.text = email


                    if(response.body()!!.data.user.phone != null){
                        phone_container.visibility = View.VISIBLE
                        parent_phone_info.text = response.body()!!.data.user.phone
                    }


                } else {
                    var intent = Intent(con, Login::class.java)
                    startActivity(intent)
                    getActivity()!!.finish();
                    gialog.dismiss()
                    //Toast.makeText(con, "Error", Toast.LENGTH_LONG).show()
                }
            }

        })

    }

    fun InternerConnection(): Boolean {
        val conn = con.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = conn.activeNetworkInfo
        if(network != null && network.isConnected){
            //  internet.visibility = View.GONE
            return true
        }else{
            //  internet.visibility = View.VISIBLE
            Snackbar.make(account_container, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.RED).show()
            return false
        }
    }

    private fun getLocalData(){

        Glide
            .with(con) // give it the context
            .load(Shared.ReturnImage()) // load the image
            .into(profileImage) // select the ImageView to load it into
    }
    fun scale() {
        val anims = AnimatorSet();
        val sX = ObjectAnimator.ofFloat(profileImage, View.SCALE_X, 0.2f, 1.0f)
        val sY = ObjectAnimator.ofFloat(profileImage, View.SCALE_Y, 0.2f, 1.0f)
        anims.playTogether(sX, sY)
        anims.setDuration(500)
        anims.interpolator = AccelerateInterpolator()
        anims.start()
    }


}
