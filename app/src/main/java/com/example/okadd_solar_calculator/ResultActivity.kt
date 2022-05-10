package com.example.okadd_solar_calculator

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.math.ceil

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.title = "Generated Result"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val panelWattage = intent.getStringExtra("panelWattage")
        val sunlightHour = intent.getStringExtra("sunlightHour")
        val batteryCapacity = intent.getStringExtra("batteryCapacity")
        val batteryVoltage = intent.getStringExtra("batteryVoltage")
        val totalLoad = intent.getStringExtra("totalLoad")
        val totalDemand = intent.getStringExtra("totalDemand")

        if (panelWattage != null && sunlightHour != null && batteryCapacity != null && batteryVoltage != null && totalLoad != null && totalDemand != null) {
            val numberOfPanels =
                ceil(totalDemand.toDouble() / (sunlightHour.toDouble() * panelWattage.toDouble())).toInt()

            textView4.text = totalDemand
            textView7.text = numberOfPanels.toString()
            "$panelWattage WATTS".also { textView8.text = it }
            val numberOfBatteries =
                ceil((2 * totalDemand.toDouble()) / (batteryCapacity.toDouble() * batteryVoltage.toDouble())).toInt()
            textView10.text = numberOfBatteries.toString()
            ("$batteryVoltage VOLTS").also { textView11.text = it }

            val inverterRating = ceil((totalLoad.toDouble() * 0.25) + totalLoad.toDouble()).toInt()
            textView13.text = inverterRating.toString()

            val chargeControllerRating = ceil(batteryCapacity.toDouble() * 0.1).toInt()
            textView16.text = chargeControllerRating.toString()
        }
        else
            Toast.makeText(this, "Something is wrong with your inputs", Toast.LENGTH_LONG).show()




        shopButton.setOnClickListener {
            val url = "https://www.jumia.com.ng/solar/"
            val shopIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(shopIntent)

        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}