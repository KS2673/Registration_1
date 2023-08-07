package com.example.mdatepicker1

import android.R
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    private var date: TextView? = null
    private var first_name: TextView? = null
    private var last_name: TextView? = null
    private var email: TextView? = null
    private var password: TextView? = null
    private var date_of_birth: TextView? = null
    private var policy: CheckBox? = null
    private var gender_group: RadioGroup? = null
    private var gender_button: RadioButton? = null
    private var select_date: Button? = null
    private var c: Calendar? = null
    private var dpd: DatePickerDialog? = null
    var db = DatabaseHelper(this)
    var filter =
        InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Pattern.compile("[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz]*")
                        .matcher(
                            source[i].toString()
                        ).matches()
                ) {
                    return@InputFilter ""
                }
            }
            null
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.mdatepicker1.R.layout.activity_main)
        gender_group = findViewById<RadioGroup>(com.example.mdatepicker1.R.id.genderGroup)
        date = findViewById<TextView>(com.example.mdatepicker1.R.id.date_of_birth)
        select_date = findViewById<Button>(com.example.mdatepicker1.R.id.select_dob)
        first_name = findViewById<TextView>(com.example.mdatepicker1.R.id.first_name)
        last_name = findViewById<TextView>(com.example.mdatepicker1.R.id.last_name)
        email = findViewById<TextView>(com.example.mdatepicker1.R.id.email)
        date_of_birth = findViewById<TextView>(com.example.mdatepicker1.R.id.date_of_birth)
        password = findViewById<TextView>(com.example.mdatepicker1.R.id.password)
        policy = findViewById<CheckBox>(com.example.mdatepicker1.R.id.policy)
        first_name = findViewById<TextView>(com.example.mdatepicker1.R.id.first_name)
        first_name?.setFilters(arrayOf(filter, LengthFilter(15)))
        last_name?.setFilters(arrayOf(filter, LengthFilter(15)))
        password?.setFilters(arrayOf(filter, LengthFilter(16)))
        select_date?.setOnClickListener(View.OnClickListener {
            c = Calendar.getInstance()
            val day = c!!.get(Calendar.DAY_OF_MONTH)
            val month = c!!.get(Calendar.MONTH)
            val year = c!!.get(Calendar.YEAR)
            dpd = DatePickerDialog(this@MainActivity,
                { view, year, month, dayOfMonth -> date!!.setText(dayOfMonth.toString() + "/" + (month + 1) + "/" + year) },
                day!!,
                month,
                year
            )
            dpd!!.show()
        })
        val b = findViewById<Button>(com.example.mdatepicker1.R.id.signUpButtonId)
        b.setOnClickListener { v ->
            val selected_gender = gender_group?.getCheckedRadioButtonId()
            gender_button = findViewById(selected_gender!!)
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
            val datePattern = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$"
            if (first_name?.getText().toString() == "") {
                first_name?.setError("Empty first name")
            } else if (last_name?.getText().toString() == "") {
                last_name?.setError("Empty last name")
            } else if (email!!.getText().toString() == "") {
                email!!.setError("Empty email address")
            } else if (!email!!.getText().toString().trim { it <= ' ' }.matches(emailPattern)) {
                email!!.setError("Invalid email address")
            } else if (date_of_birth!!.getText().toString() == "") {
                date_of_birth!!.setError("Select date of birth")
            } else {
                if (!date_of_birth!!.getText().toString().trim { it <= ' ' }
                        .matches(datePattern)) {
                    date_of_birth!!.setError("Date format should be 'mm/dd/yyyy'")
                } else if (password!!.getText().toString() == "") {
                    password!!.setError("Empty password")
                } else if (!policy!!.isChecked()) {
                    policy!!.setError("Policy must be accepted")
                } else {
                    if (first_name!!.getError() == null && last_name!!.getError() == null && email!!.getError() == null && date_of_birth!!.getError() == null && password!!.getError() == null && policy!!.isChecked()
                    ) {
                        addUser(
                            User(
                                first_name?.getText().toString(),
                                last_name?.getText().toString(),
                                gender_button?.getText().toString(),
                                email?.getText().toString(),
                                date_of_birth?.getText().toString(),
                                password?.getText().toString(),
                                Calendar.getInstance().time.toString()
                            )
                        )
                        val myIntent = Intent(v.context, UserDataShow::class.java)
                        startActivity(myIntent)
                    }
                    first_name?.setText("")
                    last_name?.setText("")
                    email?.setText("")
                    date_of_birth?.setText("")
                    password!!.setText("")
                    policy!!.setChecked(false)
                    policy!!.setError(null)
                }
            }
        }
    }

    fun addUser(newUser: User?) {
        val insertUser = db.addUser(newUser!!)
        if (insertUser) toastMessage("User added successfully") else toastMessage("Something went wrong")
    }

    private fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

private operator fun Any.not() {

 }

private fun Any.matches(regex: String): Boolean {
    return Regex(regex).matches(this as CharSequence)

 }
