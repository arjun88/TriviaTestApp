package com.appscrip.triviaapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.appscrip.triviaapp.model.GamePojo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameRepo {
    companion object {
        var gameDb: GameDb? = null

        var games: LiveData<List<GamePojo>>? = MutableLiveData()
        var gameInfo: LiveData<GamePojo>? = MutableLiveData()

        private fun initializeDB(context: Context): GameDb {
            return GameDb.getDatabaseClient(context)
        }

        fun insertData(context: Context, game: GamePojo): Long {

            gameDb = initializeDB(context)

            var saved = 0L

            CoroutineScope(IO).launch {

                saved = gameDb!!.dao().insertData(game)


            }
            return saved
        }

        fun getGamesPlayed(context: Context): LiveData<List<GamePojo>> {
            gameDb = initializeDB(context)
            games = gameDb!!.dao().getGamesPlayed()


            return games!!
        }

        /*@SuppressLint("CheckResult")
        fun getGameList(context: Context) {
            gameDb = initializeDB(context)
        }*/

        fun getGameInfo(context: Context): LiveData<GamePojo> {

                gameDb = initializeDB(context)
                gameInfo = gameDb!!.dao().getGameInfo()

            return gameInfo!!
        }

    }

}