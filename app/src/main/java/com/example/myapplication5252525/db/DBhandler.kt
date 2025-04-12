package com.example.room.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.db.dao.UserDao
import com.example.room.db.model.UserEntitly


@Database(
    entities = [UserEntitly::class], version = 1
)

abstract class DBhandler : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        const val DATABASE_NAME = "main_database"
        const val DATABASE_VERSION = 1

        const val USER_TABLE = "usertable"

        private var INSTANCE: DBhandler? = null

        fun getDatabase(context: Context): DBhandler {

            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context,
                    DBhandler::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }

    }
}
