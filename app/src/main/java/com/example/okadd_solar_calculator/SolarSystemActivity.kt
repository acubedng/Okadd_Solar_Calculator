package com.example.okadd_solar_calculator

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_solar_system.*
import kotlinx.android.synthetic.main.activity_solar_system.resultTextView


class SolarSystemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solar_system)
        supportActionBar?.title = "Solar System Preference"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val totalLoad = intent.getStringExtra("totalLoad")
        val totalDemand = intent.getStringExtra("totalDemand")




        ("Total Load : $totalLoad W\nTotal Load Demand : $totalDemand WH\nEnter the solar system specifications intended to power the load below:").also { resultTextView.text = it }




        val panel = arrayOf(
                "160",
                "240",
                "300"
        )
        val panelAdapter = ArrayAdapter<String>(this, R.layout.appliance_drop_down, panel)

        panelWattage.setAdapter(panelAdapter)

        panelWattage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                panelSize.keyListener != null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {


                when (p0.toString()) {

                    "160" -> {
                        panelSize.setText(getString(R.string.smallPanel))
                    }
                    "240" -> {
                        panelSize.setText(getString(R.string.mediumPanel))
                    }
                    "300" -> {
                        panelSize.setText(getString(R.string.largePanel))
                    }
                    else -> {
                        panelSize.setText("0")
                    }
                }
            }

        })

        val batteryVolt = arrayOf(
            "12",
            "24"
        )
        val batteryVoltAdapter = ArrayAdapter<String>(this, R.layout.appliance_drop_down, batteryVolt)

        batteryVoltage.setAdapter(batteryVoltAdapter)

        batteryVoltage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                panelSize.keyListener != null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        val battery = arrayOf(
            "120",
            "140",
            "160",
            "180",
            "200",
            "220",
            "240"
        )
        val batteryCapacityAdapter = ArrayAdapter<String>(this, R.layout.appliance_drop_down, battery)

        batteryCapacity.setAdapter(batteryCapacityAdapter)

        batteryVoltage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                panelSize.keyListener != null
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })


        resultButton.setOnClickListener {
            panelWattage.onEditorAction(EditorInfo.IME_ACTION_DONE)
            panelSize.onEditorAction(EditorInfo.IME_ACTION_DONE)
            sunlightHour.onEditorAction(EditorInfo.IME_ACTION_DONE)
            batteryCapacity.onEditorAction(EditorInfo.IME_ACTION_DONE)

            if (panelWattage.text.isNullOrEmpty() || panelSize.text.isNullOrEmpty() || sunlightHour.text.isNullOrEmpty() || batteryCapacity.text.isNullOrEmpty()) {
                Toast.makeText(this, "Text Fields must not be empty", Toast.LENGTH_LONG).show()

            } else {
                val finalPanelWattage = panelWattage.text.toString()
                val finalPanelSize = panelSize.text.toString()
                val finalSunlightHour = sunlightHour.text.toString()
                val finalBatteryVoltage = batteryVoltage.text.toString()
                val finalBatteryCapacity = batteryCapacity.text.toString()

                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("panelWattage",finalPanelWattage)
                intent.putExtra("panelSize",finalPanelSize)
                intent.putExtra("sunlightHour",finalSunlightHour)
                intent.putExtra("batteryCapacity",finalBatteryCapacity)
                intent.putExtra("batteryVoltage",finalBatteryVoltage)
                intent.putExtra("totalLoad",totalLoad)
                intent.putExtra("totalDemand",totalDemand)
                startActivity(intent)
            }
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, ApplianceActivity::class.java)
        startActivity(intent)
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}
