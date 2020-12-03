package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Periodic__Tasks

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.eschima.utopia_admin.Model.IT_Model.examination_model
import com.eschima.utopia_admin.Model.Technical_Tickets.assigned
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Examination_Adapter
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.activity_assign__finish__tasks.*
import kotlinx.android.synthetic.main.activity_examination__page.*
import kotlinx.android.synthetic.main.activity_technical_support_page.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

@Suppress("DEPRECATION")
class Assign_Finish_Tasks : AppCompatActivity() {

    lateinit var assignedAdapter: Assigned_Adapter
    lateinit var finishedAdapter: Finished_Adapter
    lateinit var Shared : Shared_Preferance
    lateinit var gialog : KProgressHUD

    lateinit var selected_item :String
    lateinit var tasks_array :ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assign__finish__tasks)

        gialog=   KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        gialog.setBackgroundColor(Color.parseColor("#eeb462"))

        tasks_rec.layoutManager = LinearLayoutManager(this)
        Shared = Shared_Preferance(this)
        tasks_array = arrayListOf("Assigned Tasks" , "Finished Tasks")

        selected_item = tasks_array[examination_type.selectedItemPosition]

        tasks_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                (tasks_spinner.getSelectedView() as TextView).setTextColor(resources.getColor(R.color.white))

                selected_item = tasks_array[tasks_spinner.selectedItemPosition]

                if(InternerConnection()){
                    if(selected_item.equals("Assigned Tasks")){
                        get_All_Assigned_Tasks(Shared.Return_Token())
                    }
                    else if(selected_item.equals("Finished Tasks")){
                        get_All_Finished_Tasks(Shared.Return_Token())
                    }
                }

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }

    override fun onResume() {
        super.onResume()

        if(InternerConnection()){
            if(selected_item.equals("Assigned Tasks")){
                get_All_Assigned_Tasks(Shared.Return_Token())
            }
            else if(selected_item.equals("Finished Tasks")){
                get_All_Finished_Tasks(Shared.Return_Token())
            }
        }
    }

    private fun get_All_Assigned_Tasks(token:String){

        gialog.show()
        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.Get_Assigned_Tasks(token)
        call.enqueue(object : Callback, retrofit2.Callback<assigned> {
            override fun onFailure(call: Call<assigned>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<assigned>, response: Response<assigned>) {

                if (response.isSuccessful){
                    gialog.dismiss()
                    if(response.body()!!.data.isNotEmpty()){
                        no_tasks_label.visibility = View.GONE
                        assignedAdapter = Assigned_Adapter(response.body()!!.data)
                        tasks_rec.adapter = assignedAdapter
                    }else{
                        no_tasks_label.visibility = View.VISIBLE
                    }


                } else {
                    gialog.dismiss()
                    //Toast.makeText(con, "Error", Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun get_All_Finished_Tasks(token:String){

        gialog.show()
        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.Get_Finished_Tasks(token)
        call.enqueue(object : Callback, retrofit2.Callback<assigned> {
            override fun onFailure(call: Call<assigned>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<assigned>, response: Response<assigned>) {

                if (response.isSuccessful){
                    gialog.dismiss()
                    if(response.body()!!.data.isNotEmpty()){
                        no_tasks_label.visibility = View.GONE
                        finishedAdapter = Finished_Adapter(response.body()!!.data)
                        tasks_rec.adapter = finishedAdapter
                    }else{
                        no_tasks_label.visibility = View.VISIBLE
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
            Snackbar.make(assign_tasks_container, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.RED).show()
            return false
        }
    }












}
