package com.demo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.model.week_diet_data_whole

class MenuListAdapter(
        val context: Context,
        val responseDTO: ArrayList<week_diet_data_whole>?,
):RecyclerView.Adapter<MenuListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, null)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MenuListAdapter.ViewHolder, position: Int) {
            if(responseDTO!!.size>0){
                holder.txtTitle.setText(responseDTO!!.get(position).day)
                holder.txtFood.setText(responseDTO!!.get(position).food)
                holder.txtTime.setText(responseDTO!!.get(position).mealTime)
            }

    }

    override fun getItemCount(): Int {
        return  responseDTO!!.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtTitle: TextView
        var txtFood: TextView
        var txtTime : TextView

        init {
            txtTitle = view.findViewById(R.id.tvTitle)
            txtFood = view.findViewById(R.id.tvFood)
            txtTime = view.findViewById(R.id.tvTime)
        }
    }
}