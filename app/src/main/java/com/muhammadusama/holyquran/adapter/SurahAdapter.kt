package com.muhammadusama.holyquran.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.muhammadusama.holyquran.databinding.SurahItemLayoutBinding
import com.muhammadusama.holyquran.models.Data

class SurahAdapter(val itemsList: List<Data>,val context:Context,val mListener: SurahItemClickListener):
    RecyclerView.Adapter<SurahAdapter.SurahViewHolder>(),Filterable {

    private lateinit var binding: SurahItemLayoutBinding
    var surahListFiltered: List<Data> = itemsList

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
        fun bind(surahData: Data,listener: SurahItemClickListener) {
            binding.surahItem = surahData
            binding.surahItemClick = listener
            binding.executePendingBindings()
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
        val surahList = surahListFiltered[position]
        holder.bind(surahList,mListener)
    }

    override fun getItemCount(): Int {
        return surahListFiltered.size
    }

    interface SurahItemClickListener{
        fun onSurahItemClicked(data: Data)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString() ?: ""
                surahListFiltered = if(charString.isEmpty()) {
                    itemsList
                }else{
                    val resultList : ArrayList<Data> = ArrayList()
                    for(row in itemsList){
                        if(row.englishName.lowercase().contains(charString.lowercase()) or row.number.toString().contains(charString)){
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResult = FilterResults()
                filterResult.values = surahListFiltered
                return  filterResult
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                surahListFiltered = p1?.values as List<Data>
                notifyDataSetChanged()
            }

        }
    }
}