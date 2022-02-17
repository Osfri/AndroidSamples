package com.example.sample24

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sample23.FragmentOne
import com.example.sample23.FragmentThree
import com.example.sample23.FragmentTwo
import kotlinx.android.synthetic.main.activity_main.*


//build.gradle 추가해야함 (두개)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = FragmentOne()
        val secondFragment = FragmentTwo()
        val thirdFragment = FragmentThree()

        setCurrentFragment(firstFragment) // 초기화 (맨처음화면)

        bottomNaviView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.person -> setCurrentFragment(firstFragment)
                R.id.Home -> setCurrentFragment(secondFragment)
                R.id.setting -> setCurrentFragment(thirdFragment)
            }
            true
        }
    }

    fun setCurrentFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
    }
}