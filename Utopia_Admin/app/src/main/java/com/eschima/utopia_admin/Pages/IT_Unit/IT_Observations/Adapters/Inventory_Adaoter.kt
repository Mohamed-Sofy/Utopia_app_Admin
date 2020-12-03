package com.eschima.utopia_admin.Pages.IT_Unit.IT_Observations.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eschima.utopia_admin.Model.IT_Model.device_inventory
import com.eschima.utopia_admin.R

class Inventory_Adaoter ( list : ArrayList<device_inventory>) : RecyclerView.Adapter<Inventory_Adaoter.inventory_ViewHolder>() {

    private var inventory_List = list
    lateinit var context : Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Inventory_Adaoter.inventory_ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(
            R.layout.device_inventory_item,
            parent, false)
        context = parent.context
        return Inventory_Adaoter.inventory_ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return inventory_List.count()
    }

    override fun onBindViewHolder(holder: Inventory_Adaoter.inventory_ViewHolder, position: Int) {

        holder.inventory_name.text = inventory_List[position].name
        holder.inventory_code.text = inventory_List[position].code
        holder.inventory_location.text = inventory_List[position].location

        holder.container.setOnClickListener {

        }
    }


    class inventory_ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var container : LinearLayout = itemView.findViewById(R.id.device_item_container)

        var inventory_name : TextView = itemView.findViewById(R.id.inventory_name)
        var inventory_code : TextView = itemView.findViewById(R.id.inventory_code)
        var inventory_location : TextView = itemView.findViewById(R.id.inventory_location)

    }

}