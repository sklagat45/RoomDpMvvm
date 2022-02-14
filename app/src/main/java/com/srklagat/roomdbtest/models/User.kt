package com.srklagat.roomdbtest.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName= "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val userName:String,
    val userEmail:String,
    val age: Int
): Parcelable
