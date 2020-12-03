package com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.IT_Model.device_inventory
import com.eschima.utopia_admin.Model.IT_Model.examination
import com.eschima.utopia_admin.R

class Examination_Adapter ( list : ArrayList<examination>) : RecyclerView.Adapter<Examination_Adapter.examination_ViewHolder>()  {

    private var examination_List = list
    lateinit var context : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Examination_Adapter.examination_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.examination_self,
            parent, false)
        context = parent.context
        return Examination_Adapter.examination_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return examination_List.count()
    }

    override fun onBindViewHolder(
        holder: Examination_Adapter.examination_ViewHolder,
        position: Int
    ) {

        holder.ex_name.text = examination_List[position].troubleshootingName
        holder.ex_reason.text = examination_List[position].troubleshootingReason
        holder.ex_date.text = examination_List[position].troubleshootingDate
        holder.ex_time.text = examination_List[position].troubleshootingTime
        holder.ex_note.text = examination_List[position].itTroubleshootingNotes
    }

    class examination_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.device_item_container)

        var ex_name : TextView = itemView.findViewById(R.id.ex_name)
        var ex_reason : TextView = itemView.findViewById(R.id.ex_reason)
        var ex_date : TextView = itemView.findViewById(R.id.ex_date)
        var ex_time : TextView = itemView.findViewById(R.id.ex_time)
        var ex_note : TextView = itemView.findViewById(R.id.ex_note)

    }
}