package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Technical_Support.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.IT_Model.examination
import com.eschima.utopia_admin.Model.Technical_Tickets.solve_ticket
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Examination_Adapter
import com.eschima.utopia_admin.R

class Solved_tickets ( list : ArrayList<solve_ticket>) : RecyclerView.Adapter<Solved_tickets.solve_ViewHolder>() {

    private var Solved_List = list
    lateinit var context : Context



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Solved_tickets.solve_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.solved_task,
            parent, false)
        context = parent.context
        return Solved_tickets.solve_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Solved_List.count()
    }

    override fun onBindViewHolder(holder: Solved_tickets.solve_ViewHolder, position: Int) {

        holder.issus_name.text = Solved_List[position].name
       // holder.issus_code.text = Solved_List[position].code
       // holder.issus_location.text = Solved_List[position].location


        val Date_time = Solved_List[position].date
        var d =Date_time.split("T")
        var date = d[0].toString()
        holder.issus_date.text = date

        val Date_time2 = Solved_List[position].time
        var t =Date_time.split("T")
        var time = t[1].toString()
        holder.issus_time.text = time

        holder.issus_action.text = Solved_List[position].executionComment

    }

    class solve_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.solved_container)

        var issus_name : TextView = itemView.findViewById(R.id.issus_name)
        var issus_code : TextView = itemView.findViewById(R.id.issus_code)
        var issus_location : TextView = itemView.findViewById(R.id.issus_location)
        var issus_date : TextView = itemView.findViewById(R.id.issus_date)
        var issus_time : TextView = itemView.findViewById(R.id.issus_time)
        var issus_action : TextView = itemView.findViewById(R.id.issus_action)
    }

}