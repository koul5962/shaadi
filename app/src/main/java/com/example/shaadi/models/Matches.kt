package com.example.shaadi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class Matches(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var results: ArrayList<Results>,
    val info: Info,
)

