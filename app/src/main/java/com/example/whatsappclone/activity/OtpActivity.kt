package com.example.whatsappclone.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.whatsappclone.R
import com.example.whatsappclone.databinding.ActivityNumberBinding
import com.example.whatsappclone.databinding.ActivityOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

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

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Please Wait...")
        builder.setTitle("Loading")
        builder.setCancelable(false)
        dialog= builder.create()
        val phoneNumber = "+92"+intent.getStringExtra("number")

        val options = PhoneAuthOptions.newBuilder(auth!!)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    TODO("Not yet implemented")
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    dialog!!.dismiss()
                    Toast.makeText(this@OtpActivity, "Please Try Again!"+phoneNumber, Toast.LENGTH_LONG).show()
                    Log.d("Failed", p0.toString())
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)
                    dialog!!.dismiss()
                    verificationId =p0
                    Log.d("Sent", p0.toString())
                    Toast.makeText(this@OtpActivity, "Code Sent Successfully", Toast.LENGTH_LONG).show()
                }
            }).build()

        PhoneAuthProvider.verifyPhoneNumber(options)

        biding!!.verifyOtpButton.setOnClickListener{
            dialog!!.show();
            if (biding!!.otpCode.text.isEmpty()){
                dialog!!.dismiss();
                Toast.makeText(this@OtpActivity, "Please enter Otp", Toast.LENGTH_LONG).show()

            }else{
                val credential= PhoneAuthProvider.getCredential(verificationId!!, biding!!.otpCode.text.toString())

                auth!!.signInWithCredential(credential)
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            startActivity(Intent(this, ProfileActivity::class.java))
                        }
                    }
            }
        }
    }
}


