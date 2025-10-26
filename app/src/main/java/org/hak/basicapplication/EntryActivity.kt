package org.hak.basicapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
    lateinit var txttime: TextView
    var selectedDOB=""
    var selectedTime=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        btnBack=findViewById(R.id.btnBack)
        txtViewDetail=findViewById(R.id.txtViewDetail)
        btnEntrySubmit=findViewById(R.id.btnEntrySubmit)
        spinnerCity=findViewById(R.id.spinnerCity)
        txtDOB=findViewById(R.id.txtDOB)
        txttime=findViewById(R.id.txttime)

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
        //DatePickerDialog
        txtDOB.setOnClickListener {
            showDatePickerDialog()
        }
        //TimePickerDialog
        txttime.setOnClickListener {
            showTimePickerDialog()
        }
    }//on create

    private fun EntryActivity.showTimePickerDialog() {
       val calendar= Calendar.getInstance()
        val timePickerDialog= TimePickerDialog(this,{_,hr,min->
          // txttime.text="$hr:$min"

            val c= Calendar.getInstance()
            c.set(Calendar.HOUR_OF_DAY,hr)
            c.set(Calendar.MINUTE,min)
            var timeFormat= SimpleDateFormat("hh:mm a",Locale.getDefault())
            selectedTime=timeFormat.format(c.time).toString()
            txttime.text=selectedTime
        },

            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false // 24 hours format
            )
        timePickerDialog.show()
    }

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
    }//showDatePickerDialog

    //menu inflate
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val result = when(item.itemId){
            R.id.itemHome->{
                intent= Intent(this, MainActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.itemSetting->{
                Toast.makeText(this,"Clicked Setting", Toast.LENGTH_LONG).show()
                true
            }
            R.id.itemSearch->{
                Toast.makeText(this,"Clicked Search", Toast.LENGTH_LONG).show()
                true
            }
            R.id.itemExit->{
                finish()
                true
            }
            else -> false
        }
        return result
    }

}//end class