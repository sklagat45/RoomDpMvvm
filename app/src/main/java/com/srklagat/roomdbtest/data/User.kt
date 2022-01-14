package com.srklagat.roomdbtest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userName:String,
    val userEmail:String,
    val age: Int
)
