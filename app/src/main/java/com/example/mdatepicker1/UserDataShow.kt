package com.example.mdatepicker1

import android.R
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDataShow : AppCompatActivity() {
    private var t: TextView? = null
    var db = DatabaseHelper(this)
    var text = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mdatepicker1.R.layout.activity_user_data_show)
        t = findViewById<TextView>(com.example.mdatepicker1.R.id.showUsers)
        t?.setMovementMethod(ScrollingMovementMethod())
        val users_data: List<User> = db.allUsers as List<User>
        for (u in users_data) {
            val log = """
                Id: ${u.id}
                First name: ${u.first_name}
                Last name: ${u.last_name}
                Gender: ${u.gender}
                Email: ${u.email}
                DOB: ${u.date_of_birth}
                Registration Date & Time: ${u.signup_date}
                
                """.trimIndent()
            text = text + log
        }
        t?.setText(text)
    }
}