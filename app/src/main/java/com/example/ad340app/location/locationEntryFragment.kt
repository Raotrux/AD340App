package com.example.ad340app.location

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

import com.example.ad340app.R

/**
 * A simple [Fragment] subclass.
 */
class LocationEntryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location_entry, container, false)

        //update UI, get view references, etc
        val submitButton: Button = view.findViewById(R.id.submitButton)
        val zipcodeEditText: EditText = view.findViewById(R.id.zipcodeEditText)

        submitButton.setOnClickListener {
            val zipcode: String = zipcodeEditText.text.toString()

            if (zipcode.length != 5) {
                Toast.makeText(requireContext(), R.string.error_output, Toast.LENGTH_SHORT).show()
            } else {
                findNavController().navigateUp()
            }
        }

        return view
    }

}
