package org.hak.basicapplication

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Locale

class EntryActivity : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var txtViewDetail: TextView
    lateinit var btnEntrySubmit: Button
    lateinit var spinnerCity: Spinner
    lateinit var txtDOB : TextView

    private var selectedDOB = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        btnBack=findViewById(R.id.btnBack)
        txtViewDetail=findViewById(R.id.txtViewDetail)
        btnEntrySubmit=findViewById(R.id.btnEntrySubmit)
        spinnerCity=findViewById(R.id.spinnerCity)

        val data=intent.extras
        var msg=data?.getString("msg")
        var id=data?.getInt("id")
        if(msg.isNullOrEmpty()){
            msg="Empty Message"
        }
        txtViewDetail.text="$msg : $id"

        btnBack.setOnClickListener {
            intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        //spinner
        val cityList=listOf("Select Your City","Yangon","Mandalay","Bago","Bagan")
        val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,cityList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCity.adapter=adapter

        btnEntrySubmit.setOnClickListener {
            val selectedCity=spinnerCity.selectedItem.toString()
            if(selectedCity==cityList[0]){
                Toast.makeText(this,"Please Your City", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Your Selected:$selectedCity", Toast.LENGTH_LONG).show()
            }
        }
        txtDOB.setOnClickListener {
            showDatePickerDialog()
        }



    }//on create

    private fun showDatePickerDialog() {
        val calendar= Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(this,{
            _,year,month,day ->
            selectedDOB ="$year / ${month+1}  / $day"
            txtDOB.text = selectedDOB
            val c = Calendar.getInstance()
            c.set(year,month,day)
            val dateFormat = SimpleDateFormat("dd/mm/yy", Locale.getDefault())


        },calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)

        )


    }
}//end class