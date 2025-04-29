package com.example.mydiary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.mydiary.data.entity.RemindEntity

@Dao
interface RemindDao {

    @Query("""Select * FROM RemindEntity""")
    suspend fun getReminds(): List<RemindEntity>

    @Delete
    suspend fun deleteRemind(remindEntity: RemindEntity)

    @Update
    suspend fun editRemind(remindEntity: RemindEntity)

    @Insert
    suspend fun addRemind(remindEntity: RemindEntity)
}