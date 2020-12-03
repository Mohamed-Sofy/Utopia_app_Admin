package com.eschima.utopia_admin.Pages.IT_Unit.IT_Support.Periodic__Tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.IT_Model.examination
import com.eschima.utopia_admin.Model.Technical_Tickets.assign
import com.eschima.utopia_admin.R

class Finished_Adapter ( list : ArrayList<assign>) : RecyclerView.Adapter<Finished_Adapter.finish_ViewHolder>() {


    private var Finish_List = list
    lateinit var context : Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Finished_Adapter.finish_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.finish_task_item,
            parent, false)
        context = parent.context
        return Finished_Adapter.finish_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Finish_List.count()
    }

    override fun onBindViewHolder(holder: Finished_Adapter.finish_ViewHolder, position: Int) {

        holder.ft_name.text = Finish_List[position].name
        holder.ft_des.text = Finish_List[position].description

        val Date_time = Finish_List[position].date
        var d =Date_time.split("T")
        var date = d[0].toString()
        holder.ft_date.text = date

        holder.ft_time.text = Finish_List[position].startTime
        holder.ft_end_time.text = Finish_List[position].endTime
        holder.ft_comment.text = Finish_List[position].executionComment
    }


    class finish_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.finish_item_container)

        var ft_name : TextView = itemView.findViewById(R.id.ft_name)
        var ft_des : TextView = itemView.findViewById(R.id.ft_des)
        var ft_date : TextView = itemView.findViewById(R.id.ft_date)
        var ft_time : TextView = itemView.findViewById(R.id.ft_time)
        var ft_end_time : TextView = itemView.findViewById(R.id.ft_end_time)


        var ft_comment : TextView = itemView.findViewById(R.id.ft_comment)

    }

}