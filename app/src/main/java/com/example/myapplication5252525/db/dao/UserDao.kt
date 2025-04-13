package com.example.room.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room.db.DBhandler
import com.example.room.db.model.UserEntitly
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    fun insertUser(vararg user: UserEntitly)

    @get:Query("SELECT * FROM ${DBhandler.USER_TABLE}")
    val getUser: Flow<List<UserEntitly>>

    @Update
    fun update(vararg user: UserEntitly): Int

    @Delete
    fun delete(user:UserEntitly)

    @Query("Delete FROM ${DBhandler.USER_TABLE}")
    fun deleteallusers()
}

