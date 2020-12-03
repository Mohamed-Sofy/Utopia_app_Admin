package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.Technical_Tickets.solve_ticket
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support.Solve_Technical_Page
import com.eschima.utopia_admin.R

class Requested_Adapter ( list : ArrayList<solve_ticket>) : RecyclerView.Adapter<Requested_Adapter.request_ViewHolder>() {

    private var Requested_List = list
    lateinit var context : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Requested_Adapter.request_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.unsolved_task,
            parent, false)
        context = parent.context
        return Requested_Adapter.request_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Requested_List.count()
    }

    override fun onBindViewHolder(holder: Requested_Adapter.request_ViewHolder, position: Int) {

        holder.issus_name.text = Requested_List[position].name
       // holder.issus_code.text = Requested_List[position].code
       // holder.issus_location.text = Requested_List[position].location

        val Date_time = Requested_List[position].date
        var d =Date_time.split("T")
        var date = d[0].toString()
        holder.issus_date.text = date

        val Date_time2 = Requested_List[position].time
        var t =Date_time.split("T")
        var time = t[1].toString()
        holder.issus_time.text = time


        holder.solve_btn.setOnClickListener {
            val intent = Intent(context , Solve_Technical_Page::class.java)
            intent.putExtra("id",Requested_List[position].id)
            intent.putExtra("name",Requested_List[position].name)
            context.startActivity(intent)
        }
    }

    class request_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.solved_container)

        var issus_name : TextView = itemView.findViewById(R.id.un_s_name)
        var issus_code : TextView = itemView.findViewById(R.id.un_s_code)
        var issus_location : TextView = itemView.findViewById(R.id.un_s_location)
        var issus_date : TextView = itemView.findViewById(R.id.un_s_date)
        var issus_time : TextView = itemView.findViewById(R.id.un_s_time)
        var solve_btn : Button = itemView.findViewById(R.id.solve_btn)
    }


}