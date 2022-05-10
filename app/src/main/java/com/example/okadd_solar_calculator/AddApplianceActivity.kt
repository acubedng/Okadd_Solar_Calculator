package com.example.okadd_solar_calculator

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.okadd_solar_calculator.ApplianceViewModel
import kotlinx.android.synthetic.main.activity_add_appliance.*

@Suppress("NAME_SHADOWING")
class AddApplianceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appliance)
        supportActionBar?.title = "New Appliance"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val appliance = arrayOf(
            "Deep freezer",
            "Iron",
            "Phone charger",
            "Laptop charger",
            "Refrigerator",
            "Deep Freezer",
            "micro-wave",
            "Television",
            "Radio",
            "Bulb",
            "Custom Appliance"
        )
        val appAdapter = ArrayAdapter<String>(this, R.layout.appliance_drop_down, appliance)

//        val editText : AutoCompleteTextView = findViewById(R.id.editText)

        editText.setAdapter(appAdapter)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                editText2.keyListener != null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {


                when (p0.toString()) {
                    "Custom Appliance" -> {
                        textInputLayout5.visibility = View.VISIBLE
                        // editText2.keyListener = null
                    }
                    else -> {
                        textInputLayout5.visibility = View.GONE
                        // editText2.inputType = TYPE_CLASS_NUMBER
                    }
                }
            }


        })

        button.setOnClickListener {

            editText.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText2.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText3.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText4.onEditorAction(EditorInfo.IME_ACTION_DONE)
            editText5.onEditorAction(EditorInfo.IME_ACTION_DONE)

            if (editText.text.isNullOrEmpty() || editText2.text.isNullOrEmpty()|| editText3.text.isNullOrEmpty()|| editText4.text.isNullOrEmpty()) {
                Toast.makeText(this, "Text Fields must not be empty", Toast.LENGTH_LONG).show()
            } else if (editText5.text.isNullOrEmpty()){
                val appliance: String = editText.text.toString()
                val wattage = editText2.text.toString().toInt()
                val hours = editText3.text.toString().toInt()
                val qty =  editText4.text.toString().toInt()



                val applianceDetails = ApplianceDetails(appliance, wattage, hours, qty)
                ApplianceViewModel(application).insert(applianceDetails)

//            val intent = Intent(this, ApplianceActivity::class.java)
//            startActivity(intent)
                finish()
            }
            else {
                val appliance: String = editText5.text.toString()
                val wattage = editText2.text.toString().toInt()
                val hours = editText3.text.toString().toInt()
                val qty = editText4.text.toString().toInt()


                val applianceDetails = ApplianceDetails(appliance, wattage, hours, qty)
                ApplianceViewModel(application).insert(applianceDetails)

                finish()
            }

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}





