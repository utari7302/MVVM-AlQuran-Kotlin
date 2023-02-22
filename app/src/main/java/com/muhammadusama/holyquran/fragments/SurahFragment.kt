package com.muhammadusama.holyquran.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.RecyclerView
import com.muhammadusama.holyquran.R
import com.muhammadusama.holyquran.adapter.SurahAdapter
import com.muhammadusama.holyquran.viewmodels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SurahFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var surahAdapter: SurahAdapter
    lateinit var surahViewModel: SurahViewModel

    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + job)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_surah, container, false)
        fetchSurahList(view)
        return view
    }

    private fun fetchSurahList(view: View?) {
        recyclerView = view!!.findViewById(R.id.surahRecyclerView)
        surahViewModel = ViewModelProvider(requireActivity())[SurahViewModel::class.java]

        uiScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                try{
                    val response = surahViewModel.getSurahFromRepository()
                    if(response != null) {
                        surahAdapter = SurahAdapter(response.data,requireActivity())
                        recyclerView.adapter = surahAdapter
                    }

                }catch (e:Exception){
                    Log.d("OSAMA", e.message.toString())
                }
            }
        }
    }

    override fun onDestroy(){
        job.cancel()
        super.onDestroy()
    }
}