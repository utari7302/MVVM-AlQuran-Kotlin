package com.muhammadusama.holyquran.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammadusama.holyquran.models.Data

@Dao
interface SurahDao {

    @Query("SELECT * FROM QuranSurah")
    fun getQuranSurahList(): Data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuranSurahListInDB(quranSurah: List<Data>)

    @Query("DELETE FROM QuranSurah")
    suspend fun deleteQuranSurah()

}