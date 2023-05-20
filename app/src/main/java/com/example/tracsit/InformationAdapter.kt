package com.example.tracsit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InformationAdapter() : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var fromLocation: TextView
        var toLocation: TextView
        var travelTime: TextView

        init {
            fromLocation = itemView.findViewById(R.id.fromLocation)
            toLocation = itemView.findViewById(R.id.toLocation)
            travelTime = itemView.findViewById(R.id.travelTime)

            itemView.setOnClickListener {
                var position: Int = getAdapterPosition()
                val context = itemView.context
                val intent = Intent(context, HomeFragment::class.java).apply {
                    putExtra("NUMBER", position)
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
        viewHolder.fromLocation.text = fromLocation[i]
        viewHolder.toLocation.text = toLocation[i]
        viewHolder.travelTime.text = travelTime[i]

    }

    override fun getItemCount(): Int {
        return fromLocation.size
    }

    private val fromLocation = arrayOf("Brussels",
        "Paris", "Rome", "Amsterdam")

    private val toLocation = arrayOf("Paris", "Rome",
        "Brussels", "Frankfurt")

    private val travelTime = arrayOf("00:10",
        "00:20", "00:30", "00:40")
}