package com.example.demomesing.features.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demomesing.R
import com.example.demomesing.features.profile.ProfileActivity
import com.example.demomesing.model.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_home.*

class Home2Activity : AppCompatActivity() {
    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        user = intent.extras?.getSerializable("objUser") as User
        init()
        btn_profile.setOnClickListener { sendProfile() }
    }

    private fun sendProfile() {
        val intent = Intent(this@Home2Activity, ProfileActivity::class.java)
        intent.putExtra("objUser", user)
        startActivity(intent)
    }


    private fun init(){
        tv_usuario.text = user.nameUser+" "+user.lastNameUser
        if (user.codeStatus==200){
            tv_dashboard.text="Dashboard "+user.status
            //val picasso = Picasso.
            Picasso.get()
                .load(user.avatar)
                .placeholder(R.color.colorPrimary)
                .into(img_profile)
        }
    }
}
