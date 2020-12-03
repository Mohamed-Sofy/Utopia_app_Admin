package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support

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
import com.eschima.utopia_admin.Model.Technical_Tickets.Solved_Tickets
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Examination_Adapter
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support.Adapter.Solved_tickets
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
class Technical_support_page : AppCompatActivity() {

    lateinit var solvedTickets: Solved_tickets
    lateinit var Shared : Shared_Preferance
    lateinit var gialog : KProgressHUD

    lateinit var selected_item :String
    lateinit var tasks_array :ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_technical_support_page)


        gialog=   KProgressHUD.create(this)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        gialog.setBackgroundColor(Color.parseColor("#eeb462"))

        technical_support_rec.layoutManager = LinearLayoutManager(this)
        Shared = Shared_Preferance(this)
        tasks_array = arrayListOf("Requested Technical Tickets" , "Solved Technical Tickets")

        selected_item = tasks_array[examination_type.selectedItemPosition]



        technical_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                (technical_spinner.getSelectedView() as TextView).setTextColor(resources.getColor(R.color.white))

                selected_item = tasks_array[technical_spinner.selectedItemPosition]

                if(InternerConnection()){
                    if(selected_item.equals("Requested Technical Tickets")){

                    }
                    else if(selected_item.equals("Solved Technical Tickets")){
                        get_All_Solved_Tichnical_tasks(Shared.Return_Token())
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
            if(selected_item.equals("Requested Technical Tickets")){
            }
            else if(selected_item.equals("Solved Technical Tickets")){
                get_All_Solved_Tichnical_tasks(Shared.Return_Token())
            }
        }
    }




    private fun get_All_Solved_Tichnical_tasks(token:String){

        gialog.show()
        var client = ServiceGenerator.createService(APIClient::class.java)
        var call = client.Get_Solved_technical_tickets(token)
        call.enqueue(object : Callback, retrofit2.Callback<Solved_Tickets> {
            override fun onFailure(call: Call<Solved_Tickets>, t: Throwable) {
                gialog.dismiss()
            }

            override fun onResponse(call: Call<Solved_Tickets>, response: Response<Solved_Tickets>) {

                if (response.isSuccessful){
                    gialog.dismiss()
                    if(response.body()!!.data.isNotEmpty()){
                        solved_no.visibility = View.GONE
                        solvedTickets = Solved_tickets(response.body()!!.data)
                        technical_support_rec.adapter = solvedTickets
                    }else{
                        solved_no.visibility = View.VISIBLE
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
            Snackbar.make(technical_cont, "No Internet Connection", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setActionTextColor(Color.RED).show()
            return false
        }
    }









}
