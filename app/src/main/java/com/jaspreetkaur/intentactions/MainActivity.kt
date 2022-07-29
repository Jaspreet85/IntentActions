package com.jaspreetkaur.intentactions

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    lateinit var etPhone : EditText
    lateinit var etSms: EditText
    lateinit var etShare: EditText
    lateinit var etMailto: EditText
    lateinit var etSubject: EditText
    lateinit var etMailMess: EditText
    lateinit var rbRating: RatingBar

    lateinit var btnCall : Button
    lateinit var btnSms: Button
    lateinit var btnShare : Button
    lateinit var btnMail : Button
    lateinit var btnRating: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPhone = findViewById(R.id.etPhone)
        etSms = findViewById(R.id.etSms)
        etShare = findViewById(R.id.etShare)
        etMailto = findViewById(R.id.etMailto)
        etSubject = findViewById(R.id.etSubject)
        etMailMess = findViewById(R.id.etMailMess)
        rbRating = findViewById(R.id.rbRating)

        btnCall = findViewById(R.id.btnCall)
        btnSms = findViewById(R.id.btnSms)
        btnShare = findViewById(R.id.btnShare)
        btnMail = findViewById(R.id.btnMail)
       btnRating = findViewById(R.id.btnRating)


        btnCall.setOnClickListener {
            val mobileNumber = etPhone.text.toString().trim()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + Uri.encode(mobileNumber))
            startActivity(dialIntent)
        }
        // set up run time permission in manifests

        checkPermission()

        btnSms.setOnClickListener {
            val mobileNumber = etPhone.text.toString()
            val message = etSms.text.toString()
            if (mobileNumber.isNotEmpty()) {
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.putExtra(Intent.EXTRA_TEXT,message)

                smsIntent.data = Uri.parse("sms: $mobileNumber ")
                startActivity(smsIntent)
            }
        }


        btnShare.setOnClickListener {
            val note :String = etShare.text.toString().trim()

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT,note)
            val chooser =Intent.createChooser(shareIntent,"Share using....")
            startActivity(chooser)
        }

        btnMail.setOnClickListener {
            val email = etMailto.text.toString().trim()
            val subject = etSubject.text.toString().trim()
            val message = etMailMess.text.toString().trim()

            val emailIntent = Intent(Intent.ACTION_SEND)

            emailIntent.type = "text/plain"
            emailIntent.data = Uri.parse("Mail to:")
            emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            emailIntent.putExtra(Intent.EXTRA_TEXT, message)
            val chooser = Intent.createChooser(emailIntent, "Send email....")
            startActivity(chooser)
        }

        btnRating.setOnClickListener {
            Toast.makeText(applicationContext,"Your rating" +rbRating.rating, Toast.LENGTH_SHORT).show()
        }


        }



    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 101)
        }
    }

}