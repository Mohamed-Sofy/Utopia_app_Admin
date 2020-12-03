package com.eschima.utopia_admin.Pages.HSE

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import com.eschima.utopia_admin.R
import kotlinx.android.synthetic.main.activity_examination__page.*
import kotlinx.android.synthetic.main.activity_healthy__activities.*

@Suppress("DEPRECATION")
class Healthy_Activities : AppCompatActivity() {


    lateinit var selected_item :String
    lateinit var HSE_array :ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_healthy__activities)

        HSE_array = arrayListOf("Healthy Activities" , "Healthy Awareness",
            "Safety Activities" , "Safety Awareness",
            "Environment Activities" , "Environment Awareness")

        selected_item = HSE_array[examination_type.selectedItemPosition]

        jse_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                (jse_spinner.getSelectedView() as TextView).setTextColor(resources.getColor(R.color.white))

                selected_item = HSE_array[jse_spinner.selectedItemPosition]



            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

    }
}
