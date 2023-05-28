package com.yusril.pitjarustest.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yusril.pitjarustest.data.model.Stores

@Database(entities = [Stores::class], version = 2)
abstract class StoreDatabase : RoomDatabase() {
    abstract fun storeDao(): StoreDao

    companion object {
        @Volatile
        private var INSTANCE: StoreDatabase? = null

        fun getDatabase(context: Context): StoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StoreDatabase::class.java,
                    "store_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
