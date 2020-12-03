package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Observation_Page
import com.eschima.utopia_admin.R
import kotlinx.android.synthetic.main.activity_it__main__page.*

class It_Main_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_it__main__page)


        it_observation_container.setOnClickListener {
            val intent = Intent(this ,Observation_Page::class.java )
            startActivity(intent)
        }

        it_support_container.setOnClickListener {
            val intent = Intent(this ,IT_Support_Main_Page::class.java )
            startActivity(intent)
        }

    }
}
