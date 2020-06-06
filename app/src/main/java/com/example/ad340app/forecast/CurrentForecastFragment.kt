package com.example.ad340app.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340app.*

import com.example.ad340app.details.ForecastDetailsFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 */
class CurrentForecastFragment : Fragment() {

    private val forecastRepository = ForecastRepository()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_current_forecast, container, false)
        val zipcode = arguments?.getString(KEY_ZIPCODE) ?: ""
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val dailyForecastList: RecyclerView = view.findViewById(R.id.dailyForecastList)

        dailyForecastList.layoutManager = LinearLayoutManager(requireContext())
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) {
            showForecastDetails(it)
        }

        dailyForecastList.adapter = dailyForecastAdapter

        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            dailyForecastAdapter.submitList(forecastItems)
        }
        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)

        val locationEntryButton: FloatingActionButton = view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener{
            showLocationEntry()
        }

        forecastRepository.loadForecast(zipcode)
        return view
    }

    private fun showLocationEntry() {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController().navigate(action)
    }

    private fun showForecastDetails(forecast: DailyForecast) {
        val action = CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp, forecast.description)
        findNavController().navigate(action)
    }

    companion object {
        const val KEY_ZIPCODE = "key_zipcode"

        fun newInstance(zipcode: String) : CurrentForecastFragment {
            val fragment = CurrentForecastFragment()

            val args = Bundle()
            args.putString(KEY_ZIPCODE, zipcode)
            fragment.arguments = args

            return fragment
        }
    }
}