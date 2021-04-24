package com.appscrip.triviaapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(tablePojo: GamePojo): Long

    @Query("SELECT * FROM game")
    fun getGamesPlayed(): LiveData<List<GamePojo>>

  /*  @Query("SELECT * FROM game")
    fun getGamesPlayedList(): Flowable<List<GamePojo>>*/

    @Query("SELECT * FROM game ORDER BY dateTime DESC LIMIT 1")
    fun getGameInfo(): LiveData<GamePojo>
}