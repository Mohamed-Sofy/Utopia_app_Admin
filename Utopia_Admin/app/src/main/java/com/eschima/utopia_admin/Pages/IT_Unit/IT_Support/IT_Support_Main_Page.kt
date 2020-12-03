package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Observation_Page
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Periodic__Tasks.Assign_Finish_Tasks
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support.Technical_support_page
import com.eschima.utopia_admin.R
import kotlinx.android.synthetic.main.activity_it__support__main__page.*

class IT_Support_Main_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it__support__main__page)


        periodic_container.setOnClickListener {
            val intent = Intent(this , Assign_Finish_Tasks::class.java )
            startActivity(intent)
        }


        tech_support_container.setOnClickListener {
            val intent = Intent(this , Technical_support_page::class.java )
            startActivity(intent)
        }

    }
}
