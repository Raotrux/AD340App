package com.example.ad340app.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.example.ad340app.*
import kotlinx.android.synthetic.main.fragment_forecast_details.*
import java.text.SimpleDateFormat
import java.util.*

private val DATE_FORMAT = SimpleDateFormat("MM-dd-yyyy")

class ForecastDetailsFragment : Fragment() {

    private val args: ForecastDetailsFragmentArgs by navArgs()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_forecast_details, container, false)

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())

        val tempText = layout.findViewById<TextView>(R.id.tempText)
        val descriptionText = layout.findViewById<TextView>(R.id.descriptionText)
        val date = layout.findViewById<TextView>(R.id.dateText)
        val icon = layout.findViewById<ImageView>(R.id.forecastIcon)


        tempText.text = formatTempForDisplay(args.temp, tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = args.description
        date.text = DATE_FORMAT.format(Date(args.date * 1000))

        val iconId = args.icon
        icon.load("http://openweathermap.org/img/wn/${iconId}@2x.png")

        return layout
    }
}