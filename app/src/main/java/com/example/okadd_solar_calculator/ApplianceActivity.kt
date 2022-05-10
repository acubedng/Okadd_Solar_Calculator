package com.example.okadd_solar_calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_appliance.*
import kotlinx.android.synthetic.main.result_page.*

class ApplianceActivity : AppCompatActivity() {

    private lateinit var viewModel: ApplianceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliance)
        supportActionBar?.title = "All Appliances"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)


        val applianceRv = findViewById<RecyclerView>(R.id.applianceRv)
        val adapter = ApplianceAdapter()
        applianceRv.adapter = adapter
        applianceRv.layoutManager = LinearLayoutManager(this)
        Log.d("XXX", "$adapter")
        viewModel = ViewModelProvider(this).get(ApplianceViewModel::class.java)
        viewModel.allAppliances
            .observe(this, Observer { appliance ->

                adapter.addAppliance(appliance)
             //   val number = appliance.size


                Log.d("XXX", "$appliance")
            })



        button2.setOnClickListener {
            adapter.sumLoad()

            if (adapter.totalLoad == 0) {
                Toast.makeText(this, "Please Add an Appliance", Toast.LENGTH_LONG).show()
            }

            else {
                val totalLoad = adapter.totalLoad.toString()
                val totalDemand = adapter.totalDemand.toString()
                val intent = Intent(this, SolarSystemActivity::class.java)
                intent.putExtra("totalLoad",totalLoad)
                intent.putExtra("totalDemand",totalDemand)
                startActivity(intent)

            }

        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddApplianceActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {

        if (resultLayout.visibility == View.VISIBLE){
            resultLayout.visibility = View.GONE
            button2.visibility = View.VISIBLE
            supportActionBar?.show()
            floatingActionButton.visibility = View.VISIBLE
        }
        else
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflate the menu to use in the action bar
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.how_to_use -> {
                val intent = Intent(this, HowtoActivity::class.java)
                startActivity(intent)
            }
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
