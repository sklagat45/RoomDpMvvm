package com.srklagat.roomdbtest.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.srklagat.roomdbtest.models.User


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Update
    suspend fun update(user:User)
    @Delete
    suspend fun deleteUser(user: User)
    @Query("DELETE FROM user_table")
    suspend fun deleteAlaaUsers()


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData():LiveData<List<User>>


}