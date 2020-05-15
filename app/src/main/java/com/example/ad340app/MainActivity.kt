package com.example.ad340app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340app.details.ForecastDetailsActivity
import com.example.ad340app.location.LocationEntryFragment

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tempDisplaySettingManager = TempDisplaySettingManager(this)

        val submitButton: Button = findViewById(R.id.submitButton)
        val zipcodeEditText: EditText = findViewById(R.id.zipcodeEditText)

        submitButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()

            if (zipcode.length != 5) {
                Toast.makeText(this, R.string.error_output, Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.loadForecast(zipcode)
            }
        }


        val dailyForecastList: RecyclerView = findViewById(R.id.dailyForecastList)

        dailyForecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) {
            showForecastDetails(it)
        }
        dailyForecastList.adapter = dailyForecastAdapter


        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, LocationEntryFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingDialog(this, tempDisplaySettingManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showForecastDetails(forecast: DailyForecast) {
        val forecastDetailsIntent = Intent(this, ForecastDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
        forecastDetailsIntent.putExtra("key_description", forecast.description)
        startActivity(forecastDetailsIntent)
    }
}




