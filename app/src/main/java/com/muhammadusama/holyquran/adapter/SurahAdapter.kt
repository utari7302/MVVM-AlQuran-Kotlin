package com.muhammadusama.holyquran.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muhammadusama.holyquran.R
import com.muhammadusama.holyquran.models.Data

class SurahAdapter(val itemsList: List<Data>,val context:Context):
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>() {

    class SurahViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val surahNumber = itemView.findViewById<TextView>(R.id.textView5)
        val surahNameEnglish = itemView.findViewById<TextView>(R.id.textView)
        val surahRevelation = itemView.findViewById<TextView>(R.id.textView2)
        val surahTotalAyah = itemView.findViewById<TextView>(R.id.textView3)
        val surahNameArabic = itemView.findViewById<TextView>(R.id.textView4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.surah_item_layout,parent,false)
        return SurahViewHolder(view);
    }

    override fun onBindViewHolder(holder: SurahViewHolder, position: Int) {
        holder.surahNumber.text = itemsList[position].number.toString()
        holder.surahNameEnglish.text = itemsList[position].englishName.toString()
        holder.surahRevelation.text = itemsList[position].revelationType.toString()
        holder.surahTotalAyah.text = itemsList[position].numberOfAyahs.toString()+ " " + "Verses"
        holder.surahNameArabic.text = itemsList[position].name.toString()
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}