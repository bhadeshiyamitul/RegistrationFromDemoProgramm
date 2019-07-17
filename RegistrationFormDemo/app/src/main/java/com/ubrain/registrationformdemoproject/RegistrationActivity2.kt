package com.ubrain.registrationformdemoproject

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*


class RegistrationActivity2 : AppCompatActivity() {

    fun onClick(view: View) {
        if (view is RadioButton) {
            Log.e("1", "radiobutton")
            var ischeck = view.isChecked
            when (view.id) {
                R.id.rd_male -> {
                    if (ischeck) Toast.makeText(
                        applicationContext,
                        "radio buttton male clicked",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                R.id.rd_female -> {
                    if (ischeck) Toast.makeText(
                        applicationContext,
                        "radio buttton female clicked",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    fun onCheckBoxCLicked(view: View) {
        if (view is CheckBox) {
            var ischeck = view.isChecked
            when (view.id) {
                R.id.reading -> {
                    if (ischeck) Toast.makeText(applicationContext, "reading checked", Toast.LENGTH_SHORT).show()
                }
                R.id.playing -> {
                    if (ischeck) Toast.makeText(applicationContext, "playing checked", Toast.LENGTH_SHORT).show()
                }
                R.id.traveling -> {
                    if (ischeck) Toast.makeText(applicationContext, "traveling checked", Toast.LENGTH_SHORT).show()
                }
                R.id.cooking -> {
                    if (ischeck) Toast.makeText(applicationContext, "cooking checked", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration2)
        supportActionBar!!.hide()
        var dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        var cityName = arrayListOf<String>("Rajkot", "Junagadh", "Surat", "Baroda", "Ahmedabad")

        var imgUser = findViewById<ImageView>(R.id.img_user)
        var userName = findViewById<EditText>(R.id.ed_user_name)
        var rdMale = findViewById<RadioButton>(R.id.rd_male)
        var rdFemale = findViewById<RadioButton>(R.id.rd_female)
        var address = findViewById<EditText>(R.id.ed_address)
        var edEmail1 = findViewById<EditText>(R.id.ed_email)
        var citySpinner = findViewById<Spinner>(R.id.spinner_city)

        var birthDate = findViewById<EditText>(R.id.ed_birthdate)

        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)

        var time = findViewById<EditText>(R.id.ed_time)

        var btnRegister = findViewById<Button>(R.id.btn_register)

        fun isValidUserName() {
            Log.e("user", "function")
            var validUserPattern = "^[a-z0-9._-]{2,25}$"
            var user1 = userName.text.toString().trim()
            if(user1.equals(""))
            {
                userName.setError("enter user name")
            }

            Log.e("user", "${user1}")
            if (user1.matches(Regex(validUserPattern))) {
                Log.e("user", "data")
                Toast.makeText(applicationContext, "valid userName", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(applicationContext, "Invalid userName", Toast.LENGTH_SHORT).show()
            }
        }

        fun isEmailValidate(): Boolean {
            Log.e("email", "function")
            var check: Boolean
            var email1 = edEmail1.text.toString().trim()
            if(email1.equals(""))
            {
                edEmail1.setError("enter email address")
            }
            Log.e("email", "${email1}")
            var validEmail = "[a-zA-Z0-9._-]+@[a-z]+(.[a-z]{2,})+"
            check = email1.matches(Regex(validEmail))
            return check
        }

        var customSpinner = customSpinner(this@RegistrationActivity2, cityName)
        citySpinner.adapter = customSpinner


        var calendar = Calendar.getInstance()

        var mYear = calendar.get(Calendar.YEAR)
        var mMonth = calendar.get(Calendar.MONTH)
        var mDay = calendar.get(Calendar.DAY_OF_MONTH)


        var hour = calendar.get(Calendar.HOUR_OF_DAY)
        var minutes = calendar.get(Calendar.MINUTE)

        time.inputType = InputType.TYPE_NULL
        time.setOnClickListener {

            var timePickerDialog = TimePickerDialog(
                this@RegistrationActivity2,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    hour = hourOfDay
                    minutes = minute
                    var timeSet = ""
                    if (hour > 12) {
                        hour -= 12
                        timeSet = "PM"
                    } else if (hour === 0) {
                        hour += 12
                        timeSet = "AM"
                    } else if (hour === 12) {
                        timeSet = "PM"
                    } else {
                        timeSet = "AM"
                    }
                    var sdf = SimpleDateFormat("hh:mm a", Locale.US)
                    var tme = "$hour:$minutes$timeSet"
                    time.setText(tme)


                },
                hour,
                minutes,
                true
            )

            timePickerDialog.show()
        }

        birthDate.inputType = InputType.TYPE_NULL
        birthDate.setOnClickListener {


            var date = DatePickerDialog(
                this@RegistrationActivity2,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->


                    birthDate.setText(dayOfMonth.toString() + "/" + (month + 1).toString() + "/" + year.toString())


                },
                mYear,
                mMonth,
                mDay
            )

            date.show()
        }







        btnRegister.setOnClickListener {


            isValidUserName()
            var emailCheck = isEmailValidate()

            if (emailCheck) {
                Toast.makeText(applicationContext, "valid Email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Invalid email", Toast.LENGTH_SHORT).show()
            }


            citySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Log.e("1", "spiner")
                    Toast.makeText(applicationContext, cityName[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
            var rating = ratingBar.rating.toString()
            Toast.makeText(applicationContext, rating, Toast.LENGTH_SHORT).show()
        }
    }
}
