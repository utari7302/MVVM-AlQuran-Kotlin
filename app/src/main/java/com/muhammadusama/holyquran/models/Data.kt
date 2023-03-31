package com.muhammadusama.holyquran.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muhammadusama.holyquran.utils.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.QURAN_SURAH)
data class Data(
    @PrimaryKey(autoGenerate = false)
    val number: Int,

    val englishName: String,
    val englishNameTranslation: String,
    val name: String,
    val numberOfAyahs: Int,
    val revelationType: String
):Parcelable