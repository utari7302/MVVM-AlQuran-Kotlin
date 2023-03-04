package com.muhammadusama.holyquran

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muhammadusama.holyquran.db.SurahDao
import com.muhammadusama.holyquran.models.Data

@Database(entities = [Data::class], version = 1)
abstract class QuranDatabase: RoomDatabase() {

    abstract fun surahDao(): SurahDao

}