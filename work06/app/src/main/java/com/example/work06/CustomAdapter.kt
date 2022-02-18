package com.example.work06

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class CustomAdapter(private val context: Context, private val dataList: MutableList<VO>) : RecyclerView.Adapter<ItemViewHolder>() {
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
    private val img = itemView.findViewById<ImageView>(R.id.view_img)
    private val title = itemView.findViewById<TextView>(R.id.view_title)
    private val addr = itemView.findViewById<TextView>(R.id.view_address)
    private val date = itemView.findViewById<TextView>(R.id.view_date)

    fun bind(dataVO: VO,context: Context){
        val file:File = File(dataVO.imgaddr)
        if (file.exists()) run {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            img.setImageBitmap(bitmap)
        }

        title.text = dataVO.title
        addr.text = dataVO.address
        date.text = dataVO.date

        itemView.setOnClickListener{
            println(dataVO.title)

            Intent(context,detail::class.java).apply {

                putExtra("data",dataVO)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            }.run { context.startActivity(this) }

        }
    }
}