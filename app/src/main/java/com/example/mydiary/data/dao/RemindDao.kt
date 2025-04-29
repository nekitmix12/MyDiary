package com.example.mydiary.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.mydiary.data.entity.RemindEntity

@Dao
interface RemindDao {

    @Query("""Select * FROM RemindEntity""")
    suspend fun getReminds(): List<RemindEntity>

    @Delete
    suspend fun deleteRemind(remindEntity: RemindEntity)
}