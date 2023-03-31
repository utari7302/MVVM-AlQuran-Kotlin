package com.muhammadusama.holyquran.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.muhammadusama.holyquran.R


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        dataReceiveAndCall(view)
        return view
    }

    private fun dataReceiveAndCall(view: View?) {
        //val data = args.surah
        //Log.d("OSAMA",data.englishName)
    }

}