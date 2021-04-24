package com.appscrip.triviaapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appscrip.triviaapp.model.GameDao
import com.appscrip.triviaapp.model.GamePojo

@Database(entities = [GamePojo::class], version = 1, exportSchema = false)
abstract class GameDb : RoomDatabase() {

    abstract fun dao(): GameDao

    companion object {

        @Volatile
        private var INSTANCE: GameDb? = null

        fun getDatabaseClient(context: Context): GameDb {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, GameDb::class.java, "TRIVIA_GAME_DB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}