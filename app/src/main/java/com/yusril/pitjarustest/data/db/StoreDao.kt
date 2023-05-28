package com.yusril.pitjarustest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yusril.pitjarustest.data.model.Stores

@Dao
interface StoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(stores: List<Stores>)

    @Query("SELECT * FROM stores ")
    fun getAllStores(): List<Stores>
}