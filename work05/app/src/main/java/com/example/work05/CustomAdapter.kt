package com.example.work05

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val context: Context,private val dataList: MutableList<DataVO>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout,parent,false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position],context)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}
class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val userGain = itemView.findViewById<TextView>(R.id.item_radio)
    private val userTitle = itemView.findViewById<TextView>(R.id.item_title)
    private val userDate = itemView.findViewById<TextView>(R.id.item_date)
    private val userPay = itemView.findViewById<TextView>(R.id.item_pay)
    private val userContent = itemView.findViewById<TextView>(R.id.item_content)

    fun bind(dataVO: DataVO,context: Context){
        userGain.text = dataVO.gain
        userTitle.text = dataVO.title
        userDate.text = dataVO.date
        userPay.text = dataVO.pay
        userContent.text = dataVO.content
    }
}