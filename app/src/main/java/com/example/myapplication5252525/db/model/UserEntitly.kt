package com.example.room.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.room.db.DBhandler

@Entity(tableName = DBhandler.USER_TABLE)
data class UserEntitly(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo var name: String,
    @ColumnInfo var family: String,
    @ColumnInfo var phone: String
)

