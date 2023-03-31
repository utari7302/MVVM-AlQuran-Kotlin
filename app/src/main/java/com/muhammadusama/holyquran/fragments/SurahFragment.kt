package com.muhammadusama.holyquran.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.muhammadusama.holyquran.R
import com.muhammadusama.holyquran.adapter.SurahAdapter
import com.muhammadusama.holyquran.models.Data
import com.muhammadusama.holyquran.viewmodels.SurahViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SurahFragment : Fragment(),SurahAdapter.SurahItemClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var searchText:EditText
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
        searchText = view!!.findViewById(R.id.searchText)
        surahViewModel = ViewModelProvider(requireActivity())[SurahViewModel::class.java]


        uiScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                try{
                    surahViewModel.getSurahFromRepository()
                    surahViewModel.objResponse.observe(viewLifecycleOwner, Observer {
                        surahAdapter = SurahAdapter(it,requireActivity(),(this@SurahFragment))
                        recyclerView.adapter = surahAdapter
                    })
                }catch (e:Exception){
                    Log.d("OSAMA", e.message.toString())
                }
            }
        }

        searchText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                surahAdapter.filter.filter(p0)
                return
            }

            override fun afterTextChanged(p0: Editable?) {
//                surahAdapter.filter.filter(p0)
//                return
            }

        })
    }

    override fun onDestroy(){
        job.cancel()
        super.onDestroy()
    }

    override fun onSurahItemClicked(data: Data) {

        Toast.makeText(requireActivity(), "Your Text Here! "+data.number, Toast.LENGTH_SHORT).show()
        //val result = SurahFragmentDirection
        val bundle = bundleOf("data" to data)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment,bundle)

    }
}