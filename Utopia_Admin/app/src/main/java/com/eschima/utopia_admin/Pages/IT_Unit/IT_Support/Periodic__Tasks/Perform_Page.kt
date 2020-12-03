package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Periodic__Tasks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.eschima.utopia_admin.Model.Technical_Tickets.submit_perform_model
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_perform__page.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class Perform_Page : AppCompatActivity() {

    lateinit var task_id : String
    lateinit var task_name :String

    lateinit var Shared: Shared_Preferance
    lateinit var dialog: SpotsDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perform__page)

        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        Shared = Shared_Preferance(this)


        task_id = intent.getStringExtra("task_id")!!
        task_name = intent.getStringExtra("task_name")!!


        save_perform_btn.setOnClickListener {
            val comment:String = perform_comment.text.toString()
            val evluation :String = perform_evaluation.text.toString()

            if(comment.isNotEmpty() &&evluation.isNotEmpty()){
                val body = submit_perform_model(task_id.toInt() , evluation,comment)
                Submit_Perform(body , Shared.Return_Token())
            }else{
                Toast.makeText(applicationContext, "Enter All Data", Toast.LENGTH_LONG).show()
            }

        }

    }


    private fun Submit_Perform(body : submit_perform_model , token :String){

        dialog.show()
        try {

            var client = ServiceGenerator.createService(APIClient::class.java)

            val call: Call<ResponseBody> = client.Perform_Assigned_Task(body , token)
            call.enqueue(object : Callback, retrofit2.Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        dialog.dismiss()
                        Toast.makeText(applicationContext, "Done", Toast.LENGTH_LONG).show()
                        finish()

                    }else{
                        dialog.dismiss()
                    }

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    dialog.dismiss()
                    Toast.makeText(applicationContext, "check Internet Connection", Toast.LENGTH_LONG).show()
                }

            })
        } catch (e: Exception) {

            Log.e("error",e.toString())
        }




    }
}
