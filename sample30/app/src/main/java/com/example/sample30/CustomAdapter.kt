package com.example.sample30

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.security.AccessControlContext
//                                                                                                  ItemViewHolder 가 class 안에있으면 customadapter.ItemViewHolder
class CustomAdapter(private val context: Context, private val dataList: ArrayList<DataVO>) : RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout,parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}
//DTO로 봐도됨 여러가지아이템을 하나로 만듬 데이터와 어댑터를 연결함
class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
    private val userPhoto = itemView.findViewById<ImageView>(R.id.img_profile)
    private val userName = itemView.findViewById<TextView>(R.id.userNameText)
    private val userPay = itemView.findViewById<TextView>(R.id.payTxt)
    private val userAddress = itemView.findViewById<TextView>(R.id.addressTxt)

    // data -> resource ( binding )
    fun bind(dataVO: DataVO,context: Context){

        // 사진
        if (dataVO.photo !=""){
            val resourceId = context.resources.getIdentifier(dataVO.photo,"drawable",context.packageName)

            if (resourceId > 0){ // 사진을 불러왔을때
                userPhoto.setImageResource(resourceId)
            }else{
//                Glide.with(itemView).load(dataVO.photo).into(userPhoto)
                userPhoto.setImageResource(R.mipmap.ic_launcher_round)
            }
        }else{//사진을 못불러왔을때 디폴트 사진 (이미지없다 아무 이미지나 뿌린다)
            userPhoto.setImageResource(R.mipmap.ic_launcher_round)
        }

        // TextView 데이터를 세팅
        userName.text = dataVO.name
        userPay.text = dataVO.pay.toString()
        userAddress.text = dataVO.address
        // ItemView 를 클릭시
        itemView.setOnClickListener{
            println(dataVO.name + " " + dataVO.photo)

            //이동 (view위에 덮어쓰기)
            Intent(context,profileDetailActivity::class.java).apply {

                // 어트로뷰트랑 같음
                putExtra("data",dataVO)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }

    }


}