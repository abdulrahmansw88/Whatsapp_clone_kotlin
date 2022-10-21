package com.example.whatsappclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityNumberBinding
import com.example.whatsappclone.databinding.ActivityOtpBinding
import com.google.firebase.auth.FirebaseAuth

class OtpActivity : AppCompatActivity() {
    private var biding: ActivityOtpBinding? = null
    private var auth: FirebaseAuth? = null
    private var verificationId: String? = null
    private var dialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        biding = ActivityOtpBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(biding!!.root)
        auth = FirebaseAuth.getInstance()
    }
}