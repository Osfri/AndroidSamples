package com.example.sample30

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

//원래 AndroidManifest.xml 에 추가해야함 안그러면 적용 안됨
class profileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        //겟 어트로뷰트
        val data = intent.getParcelableExtra<DataVO>("data")

        val imageView = findViewById<ImageView>(R.id.img_profile)
        val userName = findViewById<TextView>(R.id.user_name)
        val userId = findViewById<TextView>(R.id.user_id)
        val userPay = findViewById<TextView>(R.id.user_pay)

//        imageView.setImageResource(R.drawable.kim)

        //imageView 에 data?.photo 명의 이미지를 drawing하라
        Glide.with(this).load(getImage(data?.photo)).into(imageView)

        userPay.text = data?.pay.toString()
        userId.text = data?.id
        userName.text = data?.name
        println(data?.name + "  " + data?.id)


    }
    //이미지는 숫자로온다 근데 짐을 풀으면 String으로 와서 Int로 다시변환
    fun getImage(ImageName:String?) : Int{
        return resources.getIdentifier(ImageName,"drawable",packageName)
    }

}