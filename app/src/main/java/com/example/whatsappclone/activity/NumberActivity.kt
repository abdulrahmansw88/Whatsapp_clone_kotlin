package com.example.whatsappclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.whatsappclone.MainActivity
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityMainBinding
import com.example.whatsappclone.databinding.ActivityNumberBinding
import com.google.firebase.auth.FirebaseAuth

class NumberActivity : AppCompatActivity() {
    private var biding: ActivityNumberBinding? = null
    private var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        biding = ActivityNumberBinding.inflate(layoutInflater)
        setContentView(biding!!.root)
        super.onCreate(savedInstanceState)
        setContentView(biding!!.root)
        auth = FirebaseAuth.getInstance()

        if(auth!!.currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        biding!!.registerButton.setOnClickListener{
            Log.d("##### kjsljls" , "sss")
            Log.d("${biding!!.phoneNumber.text.isEmpty()}", "${biding!!.phoneNumber.text}")
            if(biding!!.phoneNumber.text.isEmpty()){
                Toast.makeText(this, "Please Enter you Number", Toast.LENGTH_LONG).show()
            }else{
              val intent = Intent(this, OtpActivity::class.java)
                intent.putExtra("number", biding!!.phoneNumber.text.toString())
                Log.d("number sent", biding!!.phoneNumber.text.toString())
                startActivity(intent)
            }
        }
    }
}