package com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.eschima.utopia_admin.Model.IT_Model.examination_model
import com.eschima.utopia_admin.Model.IT_Model.inventory_model
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Examination_Adapter
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Inventory_Adaoter
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_examination__page.*
import kotlinx.android.synthetic.main.activity_observation__page.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@Suppress("DEPRECATION")
class Examination_Page : AppCompatActivity() {

    lateinit var examinationAdapter: Examination_Adapter
    lateinit var Shared :Shared_Preferance
    lateinit var gialog : KProgressHUD

    lateinit var selected_item :String
    lateinit var examination_array :ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examination__page)

        gialog=   KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        gialog.setBackgroundColor(Color.parseColor("#eeb462"))


        examination_rec.layoutManager = LinearLayoutManager(this)
        Shared = Shared_Preferance(this)
        examination_array = arrayListOf("Self Troubleshooting" , "Requested Troubleshooting")

        selected_item = examination_array[examination_type.selectedItemPosition]

        examination_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                (examination_type.getSelectedView() as TextView).setTextColor(resources.getColor(R.color.white))

                selected_item = examination_array[examination_type.selectedItemPosition]

                if(InternerConnection()){
                    get_All_Examination(Shared.Return_Token())

                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        add_device_examination_btn.setOnClickListener {
            val intent = Intent(this , Add_Device_Examination::class.java )
            startActivity(intent)
        }


    }




    private fun get_All_Examination(token:String){

        gialog.show()
        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.Get_Devices_Examination(token)
        call.enqueue(object : Callback, retrofit2.Callback<examination_model> {
            override fun onFailure(call: Call<examination_model>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<examination_model>, response: Response<examination_model>) {

                if (response.isSuccessful){
                    gialog.dismiss()
                    if(response.body()!!.data.isNotEmpty()){
                        no_it_device_examination.visibility = View.GONE
                        examinationAdapter = Examination_Adapter(response.body()!!.data)
                        examination_rec.adapter = examinationAdapter
                    }else{
                        no_it_device_examination.visibility = View.VISIBLE
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
            Snackbar.make(examination_container, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.RED).show()
            return false
        }
    }
}
