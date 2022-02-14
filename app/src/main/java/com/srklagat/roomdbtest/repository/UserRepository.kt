package com.srklagat.roomdbtest.repository

import androidx.lifecycle.LiveData
import com.srklagat.roomdbtest.data.UserDao
import com.srklagat.roomdbtest.models.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.update(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAlaaUsers()
    }

}