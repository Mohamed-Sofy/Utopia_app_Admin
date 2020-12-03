package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Periodic__Tasks

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.IT_Model.examination
import com.eschima.utopia_admin.Model.Technical_Tickets.assign
import com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters.Examination_Adapter
import com.eschima.utopia_admin.R

class Assigned_Adapter ( list : ArrayList<assign>) : RecyclerView.Adapter<Assigned_Adapter.assign_ViewHolder>()  {

    private var Assign_List = list
    lateinit var context : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Assigned_Adapter.assign_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.assign_task_item,
            parent, false)
        context = parent.context
        return Assigned_Adapter.assign_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Assign_List.count()
    }

    override fun onBindViewHolder(holder: Assigned_Adapter.assign_ViewHolder, position: Int) {

        holder.at_name.text = Assign_List[position].name
        holder.at_des.text = Assign_List[position].description
        holder.at_date.text = Assign_List[position].date
        holder.at_time.text = Assign_List[position].startTime
        holder.at_end_time.text = Assign_List[position].endTime

        holder.perform_btn.setOnClickListener {
            val intent = Intent(context ,Perform_Page::class.java)
            intent.putExtra("task_id" , Assign_List[position].id)
            intent.putExtra("task_name" , Assign_List[position].name)
            context.startActivity(intent)
        }
    }


    class assign_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.assign_task_cont)

        var at_name : TextView = itemView.findViewById(R.id.at_name)
        var at_des : TextView = itemView.findViewById(R.id.at_description)
        var at_date : TextView = itemView.findViewById(R.id.at_date)
        var at_time : TextView = itemView.findViewById(R.id.at_time)
        var at_end_time : TextView = itemView.findViewById(R.id.at_end_time)

        var perform_btn : Button = itemView.findViewById(R.id.perform_btn)

    }


}