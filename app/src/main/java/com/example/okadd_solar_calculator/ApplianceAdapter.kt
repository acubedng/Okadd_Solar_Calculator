package com.example.okadd_solar_calculator

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApplianceAdapter : RecyclerView.Adapter<ApplianceAdapter.ViewHolder>() {

    private var appliances = emptyList<ApplianceDetails>()
    var totalLoad = 0
    var totalDemand = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.appliance_list, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return appliances.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val details = appliances[position]

        holder.applianceName.text =  details.applianceName
        holder.applianceRating.text = details.applianceRating.toString() + " Watt(s)"
        holder.usageHour.text = details.usageHour.toString() + " Hour(s)"
        holder.qtyTv.text = details.quantity.toString()
        holder.additionIcon.setOnClickListener {
            val newQty = (details.quantity)+1
            holder.qtyTv.text = newQty.toString()
            ApplianceViewModel(Application()).update(newQty,details.applianceName)

        }
        holder.subtractionIcon.setOnClickListener {
            if (details.quantity != 1) {
            val newQty = (details.quantity)-1
            holder.qtyTv.text = newQty.toString()
            ApplianceViewModel(Application()).update(newQty,details.applianceName)
        }}

//        holder.qtyTv.addTextChangedListener(){
//            text ->
//
//        }

        holder.deleteIcon.setOnClickListener {
            ApplianceViewModel(Application()).delete(details)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val applianceName:TextView = itemView.findViewById(R.id.appliance)
        val applianceRating: TextView = itemView.findViewById(R.id.wattage)
        val usageHour: TextView = itemView.findViewById(R.id.usageHour)
        val additionIcon : ImageView = itemView.findViewById(R.id.additionIcon)
        val subtractionIcon : ImageView = itemView.findViewById(R.id.subtractIcon)
        val deleteIcon : ImageView = itemView.findViewById(R.id.deleteIcon)
        val qtyTv : TextView = itemView.findViewById(R.id.qtyTv)
    }

    fun addAppliance(appliance : List<ApplianceDetails>){
        this.appliances = appliance
        notifyDataSetChanged()
    }
    fun sumLoad(){
        appliances.forEach {
            val load = ((it.applianceRating * it.quantity))

            val demand = ((it.applianceRating * it.quantity * it.usageHour))
            totalLoad += load
            totalDemand += demand
        }

    }
}