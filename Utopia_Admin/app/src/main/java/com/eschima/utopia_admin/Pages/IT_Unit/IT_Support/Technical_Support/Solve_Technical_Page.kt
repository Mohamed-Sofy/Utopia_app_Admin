package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.eschima.utopia_admin.Model.Technical_Tickets.solve_technical
import com.eschima.utopia_admin.Model.Technical_Tickets.submit_perform_model
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_solve__technical__page.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Solve_Technical_Page : AppCompatActivity() {

    lateinit var requested_id : String
    lateinit var requested_name :String
    lateinit var Shared: Shared_Preferance
    lateinit var dialog: SpotsDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solve__technical__page)


        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        Shared = Shared_Preferance(this)


        requested_id = intent.getStringExtra("id")!!
        requested_name = intent.getStringExtra("name")!!

        n1.text = requested_name

        save_solve_btn.setOnClickListener {

            val comment:String = ex_comment.text.toString()
            val body = solve_technical(requested_id.toInt(), comment)
            Submit_Solve(body , Shared.ReturnEmail())

        }

    }

    private fun Submit_Solve(body : solve_technical, token :String) {

        dialog.show()
        try {

            var client = ServiceGenerator.createService(APIClient::class.java)

            val call: Call<ResponseBody> = client.Solve_Technical_Task(body, token)
            call.enqueue(object : Callback, retrofit2.Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        dialog.dismiss()
                        Toast.makeText(applicationContext, "Done", Toast.LENGTH_LONG).show()
                        finish()

                    } else {
                        dialog.dismiss()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(
                        applicationContext,
                        "check Internet Connection",
                        Toast.LENGTH_LONG
                    ).show()
                }

            })
        } catch (e: Exception) {

            Log.e("error", e.toString())
        }
    }




    }
