package com.example.roomdefaultrepo

import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM Memo")
    fun getAll() :List<Memo>

    @Query("SELECT content FROM Memo")
    fun getAllContent() : List<String>

    @Insert
    fun insertData(vararg memo:Memo)

    @Delete
    fun deleteData(memo:Memo)

    @Update
    fun updateData(memo:Memo)
}