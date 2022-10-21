package com.example.whatsappclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.whatsappclone.activity.NumberActivity
import com.example.whatsappclone.adapter.ViewPagerAdapter
import com.example.whatsappclone.databinding.ActivityMainBinding
import com.example.whatsappclone.ui.CallFragment
import com.example.whatsappclone.ui.ChatkFragment
import com.example.whatsappclone.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private var biding: ActivityMainBinding? = null
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding!!.root)



        val  fragmentArrayList = ArrayList<Fragment>()
        fragmentArrayList.add(ChatkFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())
        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)
        auth = FirebaseAuth.getInstance()

        if(auth!!.currentUser == null){
            startActivity(Intent(this, NumberActivity::class.java))
            finish()
        }

        biding!!.viewPager.adapter = adapter
        biding!!.tabs.setupWithViewPager(biding!!.viewPager)





    }
}