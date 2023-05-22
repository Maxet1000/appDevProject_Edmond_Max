package com.example.tracsit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InformationAdapter : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {
    private lateinit var mListener: onItemClickListener
    private var p1 = TravelInformation("Brussels","Paris","00:20")
    private var p2 = TravelInformation( "Paris","Rome","00:10")
    private var p3 = TravelInformation("Rome", "Brussels","00:30")
    private var p4 = TravelInformation("Amsterdam","Frankfurt","00:40")
    private var travelList: ArrayList<TravelInformation> = arrayListOf(p1,p2,p3,p4)

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
                var pos: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("NUMBER", pos)
                    putExtra("CODE", fromLocation.text)
                    putExtra("CATEGORY", toLocation.text)
                    putExtra("CONTENT", travelTime.text)
                }
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cardview_information, viewGroup, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.fromLocation.text = "From: " + travelList[i].fromLocation
        viewHolder.toLocation.text = "To: " + travelList[i].toLocation
        viewHolder.travelTime.text = "TravelTime: " + travelList[i].travelTime
        viewHolder.itemView.setOnClickListener {
            if (mListener != null) {
                mListener!!.onItemClicked(i)
            }
        }
    }

    override fun getItemCount(): Int {
        return travelList.size
    }

    fun getTravelInformation(): ArrayList<TravelInformation>{
        return travelList
    }

    fun addTravelRoute() {
        travelList.add(TravelInformation("", "", ""))
    }

}