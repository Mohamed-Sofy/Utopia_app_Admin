package com.eschima.utopia_admin.Fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.eschima.utopia_admin.R
import com.kaopiz.kprogresshud.KProgressHUD
import kotlinx.android.synthetic.main.fragment_home_.*

/**
 * A simple [Fragment] subclass.
 */
class Home_Fragment : Fragment() {

    lateinit var gialog : KProgressHUD
    lateinit var con : Context
    lateinit var sections:ArrayList<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        con = context as Activity
        return inflater.inflate(R.layout.fragment_home_, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        it_container.setOnClickListener {
            
        }
    }



}
