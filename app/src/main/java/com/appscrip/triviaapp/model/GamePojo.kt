package com.appscrip.triviaapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GamePojo(
    @ColumnInfo(name = "user")
    var user: String? = "",

    @ColumnInfo(name = "q1A")
    var q1Ans: String? = "",

    @ColumnInfo(name = "q2A")
    var q2Ans: String? = "",

    @ColumnInfo(name = "dateTime")
    var dateTime: String? = "",

    ){
    @PrimaryKey(autoGenerate = true)
   // @ColumnInfo(name = "id")
    var id: Int = 0
}
