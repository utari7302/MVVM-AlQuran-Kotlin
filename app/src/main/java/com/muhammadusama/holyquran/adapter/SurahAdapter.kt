package com.muhammadusama.holyquran.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammadusama.holyquran.R
import com.muhammadusama.holyquran.databinding.SurahItemLayoutBinding
import com.muhammadusama.holyquran.models.Data

class SurahAdapter(val itemsList: List<Data>,val context:Context):
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    private lateinit var binding: SurahItemLayoutBinding

        // For simple implementation
//    class SurahViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val surahNumber = itemView.findViewById<TextView>(R.id.surahNumber)
//        val surahNameEnglish = itemView.findViewById<TextView>(R.id.surahNameEnglish)
//        val surahRevelation = itemView.findViewById<TextView>(R.id.surahRevelation)
//        val surahTotalAyah = itemView.findViewById<TextView>(R.id.surahTotalAyah)
//        val surahNameArabic = itemView.findViewById<TextView>(R.id.surahNameArabic)
//    }

    // With Data Binding
    class SurahViewHolder(
        private val binding: SurahItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(surahData: Data) {
            binding.surahItem = surahData
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        // For simple implementation
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_item_layout,parent,false)
//        return SurahViewHolder(view);
        // With Data Binding
        binding = SurahItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SurahViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        //For Simple Implementation
//        holder.surahNumber.text = itemsList[position].number.toString()
//        holder.surahNameEnglish.text = itemsList[position].englishName.toString()
//        holder.surahRevelation.text = itemsList[position].revelationType.toString()
//        holder.surahTotalAyah.text = itemsList[position].numberOfAyahs.toString()+ " " + "Verses"
//        holder.surahNameArabic.text = itemsList[position].name.toString()

        // With Data Binding
        val surahList = itemsList[position]
        holder.bind(surahList)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}