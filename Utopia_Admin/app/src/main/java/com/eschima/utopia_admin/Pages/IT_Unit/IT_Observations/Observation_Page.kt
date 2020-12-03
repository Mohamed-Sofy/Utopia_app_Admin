package com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.eschima.utopia_admin.Model.IT_Model.inventory_model
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Inventory_Adaoter
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_observation__page.*
import kotlinx.android.synthetic.main.activity_splash__screen.view.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@Suppress("DEPRECATION")
class Observation_Page : AppCompatActivity() {

    lateinit var inventoryAdaoter: Inventory_Adaoter
    lateinit var gialog : KProgressHUD
    lateinit var Shared:Shared_Preferance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_observation__page)

        device_inventory_rec.layoutManager = LinearLayoutManager(this)
        Shared = Shared_Preferance(this)

        if(InternerConnection()){
            gialog=   KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            gialog.setBackgroundColor(Color.parseColor("#eeb462"))

            get_All_Devices_Inventory(Shared.Return_Token())

        }


        add_it_device_btn.setOnClickListener {
            val intent = Intent(this , Add_Device::class.java)
            startActivity(intent)
        }

        examination_open_btn.setOnClickListener {
            val intent = Intent(this , Examination_Page::class.java)
            startActivity(intent)
        }


    }

    private fun get_All_Devices_Inventory(token:String){

        gialog.show()
        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.Get_Devices_Inventory(token)
        call.enqueue(object : Callback, retrofit2.Callback<inventory_model> {
            override fun onFailure(call: Call<inventory_model>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<inventory_model>, response: Response<inventory_model>) {

                if (response.isSuccessful){
                    gialog.dismiss()
                    if(response.body()!!.data.isNotEmpty()){
                        no_it_device_inventory.visibility = View.GONE
                        inventoryAdaoter = Inventory_Adaoter(response.body()!!.data)
                        device_inventory_rec.adapter = inventoryAdaoter
                    }else{
                        no_it_device_inventory.visibility = View.VISIBLE
                    }


                } else {
                    gialog.dismiss()
                    //Toast.makeText(con, "Error", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    @SuppressLint("MissingPermission")
    fun InternerConnection(): Boolean {
        val conn = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = conn.activeNetworkInfo
        if(network != null && network.isConnected){
            //  internet.visibility = View.GONE
            return true
        }else{
            //  internet.visibility = View.VISIBLE
            Snackbar.make(it_observation_container, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.RED).show()
            return false
        }
    }
}
