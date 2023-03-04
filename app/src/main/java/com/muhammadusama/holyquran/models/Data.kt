package com.muhammadusama.holyquran.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muhammadusama.holyquran.utils.Constants

@Entity(tableName = Constants.QURAN_SURAH)
data class Data(
    @PrimaryKey(autoGenerate = false)
    val number: Int,

    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val numberOfAyahs: Int,
    val revelationType: String
)