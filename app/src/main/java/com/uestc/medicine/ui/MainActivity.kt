package com.uestc.medicine.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uestc.medicine.R
import com.uestc.medicine.net.User
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var name: String by Delegates.notNull<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, PagingActivity::class.java)
        startActivity(intent)
        finish()
    }
}
