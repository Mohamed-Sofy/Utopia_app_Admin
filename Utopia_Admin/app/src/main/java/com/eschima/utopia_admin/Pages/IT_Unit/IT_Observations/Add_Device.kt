package com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.eschima.utopia_admin.Model.IT_Model.device_inventory_post
import com.eschima.utopia_admin.R
import com.eschima.utopia_admin.Services.APIClient
import com.eschima.utopia_admin.Services.ServiceGenerator
import com.eschima.utopia_admin.Shared_Preferance.Shared_Preferance
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_add__device.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.*
import javax.security.auth.callback.Callback

class Add_Device : AppCompatActivity() , DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    lateinit var Shared: Shared_Preferance
    lateinit var dialog: SpotsDialog
    lateinit var device_date:String


    //date
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var hour: Int = 0
    var minute: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var myHour: String = ""
    var myMinute: Int = 0
    var myAmBm: String =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__device)

        dialog = SpotsDialog.Builder().setContext(this).build() as SpotsDialog
        Shared = Shared_Preferance(this)
        device_date = ""


        device_date_btn.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog =
                DatePickerDialog(this, this, year, month,day)
            datePickerDialog.show()
        }



        add_device_btn.setOnClickListener {
            val name = device_name.text.toString()
            val code = device_code.text.toString()
            val location = device_location.text.toString()

            val price = device_price.text.toString()
            val certificate = device_cer.text.toString()
            val details = device_details.text.toString()

            if(device_date.isNotEmpty() && name.isNotEmpty() && code.isNotEmpty() && location.isNotEmpty()){
                val model =device_inventory_post(name,code,location,device_date,price,certificate,details)
                Add_IT_Device(model, Shared.Return_Token())
            }else{
                Toast.makeText(applicationContext, "Enter All Data", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myDay = dayOfMonth
        myYear = year
        myMonth = month+1
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR)
        minute = calendar.get(Calendar.MINUTE)
        val timePickerDialog = TimePickerDialog(this, this, hour, minute,false)
//.is24HourFormat(this)
        timePickerDialog.show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        //  myHour = hourOfDay
        myMinute = minute

        if (hourOfDay == 0) {
            myHour =  "12"
            myAmBm = "AM"
        } else if (hourOfDay < 12) {
            myHour =  hourOfDay.toString()
            myAmBm = "AM"
        } else if (hourOfDay == 12) {
            myHour =  hourOfDay.toString()
            myAmBm = "PM";
        } else {
            myHour =  (hourOfDay-12).toString()
            myAmBm = "PM"
        }
        device_date =""+myMonth +"-"+myDay +"-"+myYear+" "+ myHour+ ":" +myMinute+" "+myAmBm
        Log.e("dattt",device_date)
        show_device_date.text = device_date


    }

    private fun Add_IT_Device(body : device_inventory_post, token :String ){
        dialog.show()
        try {

            var client = ServiceGenerator.createService(APIClient::class.java)

            val call: Call<ResponseBody> = client.add_Device(body , token)
            call.enqueue(object : Callback, retrofit2.Callback<ResponseBody> {

                override fun onResponse(
                    call: Call<ResponseBody>, response: Response<ResponseBody>
                ) {
                    if(response.isSuccessful){
                        dialog.dismiss()
                        Toast.makeText(applicationContext, "Device Created", Toast.LENGTH_LONG).show()
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
