package com.example.tracsit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InformationAdapter : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    private var travelList: ArrayList<TravelInformation> = ArrayList()

    interface onItemClickListener{
        fun onItemClicked(position: Int)

    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var fromLocation: TextView
        var toLocation: TextView
        var travelTime: TextView

        init {

            fromLocation = itemView.findViewById(R.id.fromLocation)
            toLocation = itemView.findViewById(R.id.toLocation)
            travelTime = itemView.findViewById(R.id.travelTime)

            itemView.setOnClickListener {
                val pos: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("NUMBER", pos)
                    putExtra("FROM", fromLocation.text)
                    putExtra("TO", toLocation.text)
                    putExtra("TT", travelTime.text)
                }
                context.startActivity(intent) //Wordt momenteel niet gebruikt omdat er alleen één activity is waar het meeste in gebeurd. Maar dit is wel een handige uitbreiding
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_information, viewGroup, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.fromLocation.text = "From: " + travelList[i].fromLocation?.getAddressLine(0)
        viewHolder.toLocation.text = "To: " + travelList[i].toLocation?.getAddressLine(0)
        viewHolder.travelTime.text = "TravelTime: " + travelList[i].travelTime
        if( travelList[i].fromLocation == null){ viewHolder.fromLocation.text = "From: "}
        if( travelList[i].toLocation == null){ viewHolder.toLocation.text = "To: "}
        if( travelList[i].travelTime == null){ viewHolder.travelTime.text = "TravelTime: "}
        viewHolder.itemView.setOnClickListener {
            mListener.onItemClicked(i)
        }
    }

    override fun getItemCount(): Int {
        return travelList.size
    }

    fun getTravelInformation(): ArrayList<TravelInformation>{
        return travelList
    }

    fun addTravelRoute() {
        travelList.add(TravelInformation(null, null, null, Array(7) { false }))
    }

}